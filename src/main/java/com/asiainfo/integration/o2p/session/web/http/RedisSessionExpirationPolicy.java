package com.asiainfo.integration.o2p.session.web.http;

import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisOperations;


final class RedisSessionExpirationPolicy {

    private final RedisOperations<String,ExpiringSession> sessionRedisOperations;

    private final RedisOperations<String,String> expirationRedisOperations;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisSessionExpirationPolicy(RedisOperations sessionRedisOperations) {
        super();
        this.sessionRedisOperations = sessionRedisOperations;
        this.expirationRedisOperations = sessionRedisOperations;
    }

    public void onDelete(ExpiringSession session) {
        long lastAccessedTime = session.getLastAccessedTime();
        int maxInactiveInterval = session.getMaxInactiveIntervalInSeconds();

        long toExpire = roundUpToNextMinute(lastAccessedTime, maxInactiveInterval);
        String expireKey = getExpirationKey(toExpire);
        expirationRedisOperations.boundSetOps(expireKey).remove(session.getId());
    }

    public void onExpirationUpdated(Long originalExpirationTime, ExpiringSession session) {
        if(originalExpirationTime != null) {
            String expireKey = getExpirationKey(originalExpirationTime);
            expirationRedisOperations.boundSetOps(expireKey).remove(session.getId());
        }

        long toExpire = roundUpToNextMinute(session.getLastAccessedTime(), session.getMaxInactiveIntervalInSeconds());

        String expireKey = getExpirationKey(toExpire);
        expirationRedisOperations.boundSetOps(expireKey).add(session.getId());

        long redisExpirationInSeconds = session.getMaxInactiveIntervalInSeconds();
        String sessionKey = getSessionKey(session.getId());
        expirationRedisOperations.boundSetOps(expireKey).expire(redisExpirationInSeconds, TimeUnit.SECONDS);
        sessionRedisOperations.boundHashOps(sessionKey).expire(redisExpirationInSeconds, TimeUnit.SECONDS);
    }

    private String getExpirationKey(long expires) {
        return String.valueOf(expires); 
    }

    private String getSessionKey(String sessionId) {
        return sessionId;
    }

    public void cleanExpiredSessions() {
        long now = System.currentTimeMillis();
        long prevMin = roundDownMinute(now);

        String expirationKey = getExpirationKey(prevMin);
        Set<String> sessionsToExpire = expirationRedisOperations.boundSetOps(expirationKey).members();
        touch(expirationKey);
        for(String session : sessionsToExpire) {
            String sessionKey = getSessionKey(session);
            touch(sessionKey);
        }
    }

    private void touch(String key) {
        sessionRedisOperations.hasKey(key);
    }

    private long roundUpToNextMinute(long timeInMs, int inactiveIntervalInSec) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timeInMs + TimeUnit.SECONDS.toMillis(inactiveIntervalInSec));
        date.add(Calendar.MINUTE, 1);
        date.clear(Calendar.SECOND);
        date.clear(Calendar.MILLISECOND);
        return date.getTimeInMillis();
    }

    private long roundDownMinute(long timeInMs) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timeInMs);
        date.add(Calendar.MINUTE, -1);
        date.clear(Calendar.SECOND);
        date.clear(Calendar.MILLISECOND);
        return date.getTimeInMillis();
    }
}
