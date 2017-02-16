package com.asiainfo.integration.o2p.session.web.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface SsoRequestResponsePostProcessor {

    HttpServletRequest wrapRequest(HttpServletRequest request,
            HttpServletResponse response);


    HttpServletResponse wrapResponse(HttpServletRequest request,
            HttpServletResponse response);
}
