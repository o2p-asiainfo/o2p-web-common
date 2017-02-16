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

/**
 * @ClassName: XssFilter
 * @Description: 
 * @author zhengpeng
 * @date 2015-2-3 上午9:52:11
 *
 */
public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		 XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest)request);  
		 ((HttpServletResponse)response).addHeader("x-frame-options","SAMEORIGIN"); 
		 filterChain.doFilter(xssRequest, response);  
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
