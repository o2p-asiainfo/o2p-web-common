package com.ailk.eaap.o2p.common.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter{

	FilterConfig filterConfig = null;  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;  
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		Map map = request.getParameterMap();
		
		NewXssHttpServletRequestWraper xssRequest = new NewXssHttpServletRequestWraper((HttpServletRequest)request,map);  
		
		
		chain.doFilter(xssRequest, response);  
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig = null;
	}

}
