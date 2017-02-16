package com.asiainfo.integration.o2p.session.web.http;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


abstract class OncePerRequestFilter implements Filter {

    public static final String ALREADY_FILTERED_SUFFIX = ".FILTERED";

    private String alreadyFilteredAttributeName = getClass().getName().concat(ALREADY_FILTERED_SUFFIX);


    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        boolean hasAlreadyFilteredAttribute = request.getAttribute(alreadyFilteredAttributeName) != null;

        if (hasAlreadyFilteredAttribute) {
            filterChain.doFilter(request, response);
        }else {
            request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);
            try {
                doFilterInternal(httpRequest, httpResponse, filterChain);
            }finally {
                request.removeAttribute(alreadyFilteredAttributeName);
            }
        }
    }



    protected abstract void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException;

    public void init(FilterConfig config) {
    }

    public void destroy() {}
}
