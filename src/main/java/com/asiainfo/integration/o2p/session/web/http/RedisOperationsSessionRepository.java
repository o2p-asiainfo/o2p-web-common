package com.asiainfo.integration.o2p.session.web.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;

import com.ailk.eaap.op2.util.SpringContextUtil;
import com.asiainfo.foundation.log.Logger;


public class RedisOperationsSessionRepository implements SessionRepository<RedisOperationsSessionRepository.RedisSession> {

	private static Logger logger = Logger.getLog(RedisOperationsSessionRepository.class);
	
//    static final String BOUNDED_HASH_KEY_PREFIX = "session:sessions:";

    static final String CREATION_TIME_ATTR = "creationTime";

    static final String MAX_INACTIVE_ATTR = "maxInactiveInterval";

    static final String LAST_ACCESSED_ATTR = "lastAccessedTime";

//    static final String SESSION_ATTR_PREFIX = "sessionAttr:";

    private final RedisOperations<String,ExpiringSession> sessionRedisOperations;
    
    private final RedisSessionExpirationPolicy expirationPolicy;

    private Integer defaultMaxInactiveInterval;
    
    
    public RedisOperationsSessionRepository() {
        this.sessionRedisOperations = this.createDefaultTemplate();
        this.expirationPolicy = new RedisSessionExpirationPolicy(sessionRedisOperations);
    }


    public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
        this.defaultMaxInactiveInterval = defaultMaxInactiveInterval;
    }

    public void save(RedisSession session) {
        session.saveDelta();
    }
    
    @Scheduled(cron="0 * * * * *")
    public void cleanupExpiredSessions() {
        this.expirationPolicy.cleanExpiredSessions();
    }

    public RedisSession getSession(String id) {
        return getSession(id, false);
    }

    
    private RedisSession getSession(String id, boolean allowExpired) {
        Map<Object, Object> entries = getSessionBoundHashOperations(id).entries();
        if(entries.isEmpty()) {
            return null;
        }
        MapSession loaded = new MapSession();
        loaded.setId(id);
        for(Map.Entry<Object,Object> entry : entries.entrySet()) {
            String key = (String) entry.getKey();
            if(CREATION_TIME_ATTR.equals(key)) {
                loaded.setCreationTime((Long) entry.getValue());
            } else if(MAX_INACTIVE_ATTR.equals(key)) {
                loaded.setMaxInactiveIntervalInSeconds((Integer) entry.getValue());
            } else if(LAST_ACCESSED_ATTR.equals(key)) {
                loaded.setLastAccessedTime((Long) entry.getValue());
            } else{
            	loaded.setAttribute(key, entry.getValue());
            }
        }
        if(!allowExpired && loaded.isExpired()) {
            return null;
        }
        RedisSession result = new RedisSession(loaded);
        result.originalLastAccessTime = loaded.getLastAccessedTime() + TimeUnit.SECONDS.toMillis(loaded.getMaxInactiveIntervalInSeconds());
        result.setLastAccessedTime(System.currentTimeMillis());
        return result;
    }

    public void delete(String sessionId) {
        ExpiringSession session = getSession(sessionId, true);
        if(session == null) {
            return;
        }

        String key = getKey(sessionId);
        expirationPolicy.onDelete(session);

        this.sessionRedisOperations.delete(key);
    }

    public RedisSession createSession() {
        RedisSession redisSession = new RedisSession();
        if(defaultMaxInactiveInterval != null) {
            redisSession.setMaxInactiveIntervalInSeconds(defaultMaxInactiveInterval);
        }
        return redisSession;
    }

    static String getKey(String sessionId) {
        return sessionId;
    }

    static String getSessionAttrNameKey(String attributeName) {
        return attributeName;
    }

    private BoundHashOperations<String, Object, Object> getSessionBoundHashOperations(String sessionId) {
        String key = getKey(sessionId);
        return this.sessionRedisOperations.boundHashOps(key);
    }
    
    

    private RedisTemplate<String, ExpiringSession> createDefaultTemplate() {
    	RedisConnectionFactory connectionFactory = (RedisConnectionFactory) SpringContextUtil.getBean("sessionRedisConnectionFactory");
		RedisTemplate<String, ExpiringSession> template = new RedisTemplate<String, ExpiringSession>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }


    final class RedisSession implements ExpiringSession {
        private final MapSession cached;
        private Long originalLastAccessTime;
        private Map<String, Object> delta = new HashMap<String,Object>();


        RedisSession() {
            this(new MapSession());
            delta.put(CREATION_TIME_ATTR, getCreationTime());
            delta.put(MAX_INACTIVE_ATTR, getMaxInactiveIntervalInSeconds());
            delta.put(LAST_ACCESSED_ATTR, getLastAccessedTime());
        }


        RedisSession(MapSession cached) {
            this.cached = cached;
            delta.putAll(cached.getSessionAttrs());
        }

        public void setLastAccessedTime(long lastAccessedTime) {
            cached.setLastAccessedTime(lastAccessedTime);
            delta.put(LAST_ACCESSED_ATTR, getLastAccessedTime());
        }

        public boolean isExpired() {
            return cached.isExpired();
        }

        public long getCreationTime() {
            return cached.getCreationTime();
        }

        public String getId() {
            return cached.getId();
        }

        public long getLastAccessedTime() {
            return cached.getLastAccessedTime();
        }

        public void setMaxInactiveIntervalInSeconds(int interval) {
            cached.setMaxInactiveIntervalInSeconds(interval);
            delta.put(MAX_INACTIVE_ATTR, getMaxInactiveIntervalInSeconds());
        }

        public int getMaxInactiveIntervalInSeconds() {
            return cached.getMaxInactiveIntervalInSeconds();
        }

        public Object getAttribute(String attributeName) {
            return cached.getAttribute(attributeName);
        }

        public Set<String> getAttributeNames() {
            return cached.getAttributeNames();
        }

        public void setAttribute(String attributeName, Object attributeValue) {
            cached.setAttribute(attributeName, attributeValue);
            delta.put(getSessionAttrNameKey(attributeName), attributeValue);
        }

        public void removeAttribute(String attributeName) {
            cached.removeAttribute(attributeName);
            delta.put(getSessionAttrNameKey(attributeName), null);
        }


        private void saveDelta() {
            String sessionId = getId();
            getSessionBoundHashOperations(sessionId).putAll(delta);
            delta = new HashMap<String,Object>(delta.size());
            
            expirationPolicy.onExpirationUpdated(originalLastAccessTime, this);
        }
    }
}
