package com.asiainfo.integration.o2p.session.web.sso;

import javax.servlet.http.HttpSession;

import com.asiainfo.integration.o2p.session.web.sso.SsoRedisOperationsSessionRepository.RedisSession;

public interface SsoSessionRepository {

	RedisSession createSession(HttpSession session);

    void save(RedisSession session);

    RedisSession getSession(String id);

    void delete(String id);
}