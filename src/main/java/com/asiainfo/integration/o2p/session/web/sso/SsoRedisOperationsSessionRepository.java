package com.asiainfo.integration.o2p.session.web.sso;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;

import com.ailk.eaap.op2.util.SpringContextUtil;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;


public class SsoRedisOperationsSessionRepository implements SsoSessionRepository {

	private static Logger logger = Logger.getLog(SsoRedisOperationsSessionRepository.class);

    static final String CREATION_TIME_ATTR = "creationTime";

    static final String MAX_INACTIVE_ATTR = "maxInactiveInterval";

    static final String LAST_ACCESSED_ATTR = "lastAccessedTime";

    private final RedisOperations<String,UserRoleInfo> sessionRedisOperations;
    
    private final SsoRedisSessionExpirationPolicy expirationPolicy;

    private Integer defaultMaxInactiveInterval;
    
    
    public SsoRedisOperationsSessionRepository() {
        this.sessionRedisOperations = this.createDefaultTemplate();
        this.expirationPolicy = new SsoRedisSessionExpirationPolicy(sessionRedisOperations);
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
        UserSession loaded = new UserSession();
        loaded.setId(id);
        for(Map.Entry<Object,Object> entry : entries.entrySet()) {
            String key = (String) entry.getKey();
            if(CREATION_TIME_ATTR.equals(key)) {
                loaded.setCreationTime((Long) entry.getValue());
            } else if(MAX_INACTIVE_ATTR.equals(key)) {
                loaded.setMaxInactiveIntervalInSeconds((Integer) entry.getValue());
            } else if(LAST_ACCESSED_ATTR.equals(key)) {
                loaded.setLastAccessedTime((Long) entry.getValue());
            } else if(WebConstants.O2P_SSO_USER_SESSION_KEY.equals(key)){
            	loaded.setSsoUserInfo((UserRoleInfo) entry.getValue());
            }else if(WebConstants.O2P_PORTAL_USER_SESSION_KEY.equals(key)){
            	loaded.setPortalUserInfo((UserRoleInfo) entry.getValue());
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
        RedisSession session = getSession(sessionId, true);
        if(session == null) {
            return;
        }
        expirationPolicy.onDelete(session);
        this.sessionRedisOperations.delete(sessionId);
    }

    public RedisSession createSession(HttpSession session) {
        RedisSession redisSession = new RedisSession(new UserSession(session));
        if(defaultMaxInactiveInterval != null) {
            redisSession.setMaxInactiveIntervalInSeconds(defaultMaxInactiveInterval);
        }
        return redisSession;
    }


    private BoundHashOperations<String, Object, Object> getSessionBoundHashOperations(String sessionId) {
        return this.sessionRedisOperations.boundHashOps(sessionId);
    }
    
    

    private RedisTemplate<String, UserRoleInfo> createDefaultTemplate() {
    	RedisConnectionFactory connectionFactory = (RedisConnectionFactory) SpringContextUtil.getBean("sessionRedisConnectionFactory");
		RedisTemplate<String, UserRoleInfo> template = new RedisTemplate<String, UserRoleInfo>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }


    final class RedisSession{
        private final UserSession cached;
        private Long originalLastAccessTime;
        private Map<String, Object> delta = new HashMap<String,Object>();
        
        RedisSession() {
            this(new UserSession()); 
        }

        RedisSession(UserSession cached) {
            this.cached = cached;
            delta.put(CREATION_TIME_ATTR, cached.getCreationTime());
            delta.put(MAX_INACTIVE_ATTR, cached.getMaxInactiveIntervalInSeconds());
            delta.put(LAST_ACCESSED_ATTR, cached.getLastAccessedTime());
            delta.put(WebConstants.O2P_SSO_USER_SESSION_KEY, cached.getSsoUserInfo());
            delta.put(WebConstants.O2P_PORTAL_USER_SESSION_KEY, cached.getPortalUserInfo());
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
        
        public UserRoleInfo getSsoUserInfo() {
			return cached.getSsoUserInfo();
		}

		public void setSsoUserInfo(UserRoleInfo ssoUserInfo) {
			this.cached.setSsoUserInfo(ssoUserInfo);
			delta.put(WebConstants.O2P_SSO_USER_SESSION_KEY, cached.getSsoUserInfo());
		}

		public UserRoleInfo getPortalUserInfo() {
			return cached.getPortalUserInfo();
		}

		public void setPortalUserInfo(UserRoleInfo portalUserInfo) {
			this.cached.setPortalUserInfo(portalUserInfo);
			delta.put(WebConstants.O2P_PORTAL_USER_SESSION_KEY, cached.getPortalUserInfo());
		}
		
		public void setHttpSession(HttpSession session){
			this.cached.setHttpSession(session);
			delta.put(CREATION_TIME_ATTR, cached.getCreationTime());
            delta.put(MAX_INACTIVE_ATTR, cached.getMaxInactiveIntervalInSeconds());
            delta.put(LAST_ACCESSED_ATTR, cached.getLastAccessedTime());
            delta.put(WebConstants.O2P_SSO_USER_SESSION_KEY, cached.getSsoUserInfo());
            delta.put(WebConstants.O2P_PORTAL_USER_SESSION_KEY, cached.getPortalUserInfo());
		}

        private void saveDelta() {
        	 String sessionId = getId();
             getSessionBoundHashOperations(sessionId).putAll(delta);
             delta = new HashMap<String,Object>(delta.size());
             
             expirationPolicy.onExpirationUpdated(originalLastAccessTime, this);
        }

        
    }
}
