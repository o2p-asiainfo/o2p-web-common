package com.asiainfo.integration.o2p.session.web.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.ailk.eaap.op2.util.SpringContextUtil;
import com.asiainfo.foundation.log.Logger;
/**
 * @ClassName: MemcachedSessionRepository
 * @Description: 
 * @author zhengpeng
 * @date 2015-5-6 下午1:45:10
 *
 */
public class MemcachedSessionRepository implements SessionRepository<MemcachedSessionRepository.MemcachedSession>{
	
	private static Logger logger = Logger.getLog(MemcachedSessionRepository.class);
	
//	static final String BOUNDED_HASH_KEY_PREFIX = "session:sessions:";
	
    static final String CREATION_TIME_ATTR = "creationTime";

    static final String MAX_INACTIVE_ATTR = "maxInactiveInterval";

    static final String LAST_ACCESSED_ATTR = "lastAccessedTime";
    
//    static final String SESSION_ATTR_PREFIX = "sessionAttr:";
    
    private Integer defaultMaxInactiveInterval = MapSession.DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
    
    private MemcachedClient memcachedClient;
    
    public MemcachedSessionRepository(){
    }
    
    private MemcachedClient getMemcachedClient(){
    	if(memcachedClient==null){
			memcachedClient = (MemcachedClient)SpringContextUtil.getBean("xMemcachedClientFactoryBean");
	     }
		return memcachedClient; 
    }
    
    public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
        this.defaultMaxInactiveInterval = defaultMaxInactiveInterval;
    }
    
    public void save(MemcachedSession session) {
    	try {
			session.saveDelta();
		} catch (Exception e) {
		} 
    }
    
    static String getKey(String sessionId) {
        return sessionId;
    }
    
    static String getSessionAttrNameKey(String attributeName) {
        return attributeName;
    }
    
    public void cleanupExpiredSessions() {
    }
	
    private MemcachedSession getSession(String sessionId, boolean allowExpired) throws TimeoutException, InterruptedException, MemcachedException {
        Map<String, Object> entries = this.getSessionValue(sessionId); 
        if(entries == null || entries.isEmpty()) {
            return null;
        }
        MapSession loaded = new MapSession();
        loaded.setId(sessionId);
        for(Map.Entry<String,Object> entry : entries.entrySet()) {
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
        MemcachedSession result = new MemcachedSession(loaded);
        result.setLastAccessedTime(System.currentTimeMillis());
        return result;
    }
    
    public void delete(String sessionId) {
		try {
			String key = getKey(sessionId);
			this.getMemcachedClient().delete(key); 
		} catch (Exception e) {
		} 
    }
    
    public MemcachedSession createSession() {
    	MemcachedSession o2pSession = new MemcachedSession();
        if(defaultMaxInactiveInterval != null) {
        	o2pSession.setMaxInactiveIntervalInSeconds(defaultMaxInactiveInterval);
        }
        return o2pSession;
    }
    
    public MemcachedSession getSession(String id) { 
    	MemcachedSession o2pSession = null;
    	try {
			o2pSession = getSession(id, false);
			if(o2pSession == null){
				o2pSession = this.createSession();
			}
		} catch (Exception e) {
			o2pSession = this.createSession();
		} 
    	return o2pSession;
    }
    

	private Map<String,Object> getSessionValue(String sessionId) throws TimeoutException, InterruptedException, MemcachedException {
		Map<String,Object> result = this.getMemcachedClient().get(sessionId);
		logger.debug("######## getSessionValue key:" + sessionId + "|| MaxTime:" + defaultMaxInactiveInterval + "######## data:"+ result);
        return result;
    }
    
	
	final class MemcachedSession implements ExpiringSession {
        private final MapSession cached;
        private Map<String, Object> delta = new HashMap<String,Object>();


        MemcachedSession() {
            this(new MapSession());
            delta.put(CREATION_TIME_ATTR, getCreationTime());
            delta.put(MAX_INACTIVE_ATTR, getMaxInactiveIntervalInSeconds());
            delta.put(LAST_ACCESSED_ATTR, getLastAccessedTime());
        }


        MemcachedSession(MapSession cached) {
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
        
        private void saveDelta() throws TimeoutException, InterruptedException, MemcachedException {
            String sessionId = getId();
            String key = getKey(sessionId);
            logger.info("######## saveSessionValue key:" + key + "|| date:" + delta + "|| MaxTime:" + getMaxInactiveIntervalInSeconds());
            getMemcachedClient().set(key,1800,delta);
//          delta = new HashMap<String,Object>(delta.size());
        }
    }

}
