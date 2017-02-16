package com.asiainfo.integration.o2p.session.web.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface HttpSessionManager {

    String getCurrentSessionAlias(HttpServletRequest request);


    Map<String, String> getSessionIds(HttpServletRequest request);


    String encodeURL(String url, String sessionAlias);


    String getNewSessionAlias(HttpServletRequest request);
}
