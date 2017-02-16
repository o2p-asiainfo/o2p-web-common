package com.asiainfo.integration.o2p.session.web.http;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ailk.eaap.o2p.common.interceptor.XssHttpServletRequestWraper;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.util.StringUtil;


public class O2pSessionRepositoryFilter extends OncePerRequestFilter {
    
	private O2pSessionRepositoryFilterPolicy<RedisOperationsSessionRepository.RedisSession> o2pSessionRepositoryFilterPolicy = null;
	private FilterConfig filterConfig;
	private boolean isRepository = false;
	
	public O2pSessionRepositoryFilter() {}
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	if(isRepository){
    		this.o2pSessionRepositoryFilterPolicy.doFilterInternal(request, response, filterChain);
    	}else{
    		XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest)request);  
    		//((HttpServletResponse)response).addHeader("x-frame-options","SAMEORIGIN"); 
    		filterChain.doFilter(xssRequest, response); 
    	}
    }
    
    public void init(FilterConfig config) {
    	this.filterConfig = config;
    	this.isRepository = this.getIsRepository();
    	if(isRepository){
	    	RedisOperationsSessionRepository redisOperationsSessionRepository = new RedisOperationsSessionRepository();
	    	o2pSessionRepositoryFilterPolicy = new O2pSessionRepositoryFilterPolicy<RedisOperationsSessionRepository.RedisSession>(redisOperationsSessionRepository,filterConfig.getServletContext());
	    	HttpSessionStrategy httpSessionStrategy = new CookieHttpSessionStrategy();
	    	o2pSessionRepositoryFilterPolicy.setHttpSessionStrategy(httpSessionStrategy);
    	}
    }
    
    private boolean getIsRepository(){
    	boolean _isRepository = false;
    	String isRepositoryStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_SESSION_REPOSITORY);
    	if(!StringUtil.isEmpty(isRepositoryStr)){
    		_isRepository = Boolean.valueOf(isRepositoryStr);
    	}
    	return _isRepository;
    }


}
