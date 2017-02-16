package com.asiainfo.integration.o2p.session.web.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asiainfo.integration.o2p.session.web.sso.SsoRedisOperationsSessionRepository.RedisSession;

public interface SsoHttpSessionStrategy {

    String getRequestedSessionId(HttpServletRequest request);

    void onNewSession(RedisSession redisSession,HttpServletRequest request, HttpServletResponse response);

    void onInvalidateSession(HttpServletRequest request, HttpServletResponse response);
}
