package com.asiainfo.integration.o2p.session.web.http;


public interface ExpiringSession extends Session {

    long getCreationTime();


    long getLastAccessedTime();


    void setMaxInactiveIntervalInSeconds(int interval);


    int getMaxInactiveIntervalInSeconds();


    boolean isExpired();

}
