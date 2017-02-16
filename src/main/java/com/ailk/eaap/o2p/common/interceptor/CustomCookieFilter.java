package com.ailk.eaap.o2p.common.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asiainfo.integration.o2p.session.web.http.CookieUtil;
import com.asiainfo.integration.o2p.session.web.http.O2pCookie;

/**
 * @ClassName: CustomCookieFilter
 * @Description: 
 * @author zhengpeng
 * @date 2015-5-26 下午5:42:41
 *
 */
public class CustomCookieFilter implements Filter{
	
	private static final String CUSTOM_SESSION_ID = "JSESSIONID";
	private static final String HTTP_ONLY = "HttpOnly";
	private static final String SET_COOKIE = "SET-COOKIE";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest _request = (HttpServletRequest) request;
		  HttpServletResponse _response = (HttpServletResponse) response;
		  if (!_response.containsHeader(SET_COOKIE)) {
			  String sessionId = _request.getSession().getId();
			  O2pCookie o2pCookie = new O2pCookie(CUSTOM_SESSION_ID,sessionId);
			  o2pCookie.setMaxAge(1800);
			  o2pCookie.setHttpOnly(true);
			  CookieUtil.addCookie(o2pCookie, _response);
		  }	
		  chain.doFilter(_request, _response);  
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
}
