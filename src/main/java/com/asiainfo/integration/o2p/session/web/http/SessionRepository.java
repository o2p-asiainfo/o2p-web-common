package com.asiainfo.integration.o2p.session.web.http;

public interface SessionRepository<S extends Session> {

    S createSession();

    void save(S session);

    S getSession(String id);

    void delete(String id);
}