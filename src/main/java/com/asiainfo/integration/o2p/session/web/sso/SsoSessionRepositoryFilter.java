package com.asiainfo.integration.o2p.session.web.sso;

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


public class SsoSessionRepositoryFilter extends OncePerRequestFilter {
    
	private SsoSessionRepositoryFilterPolicy ssoSessionRepositoryFilterPolicy = null;
	private FilterConfig filterConfig;
	private boolean isRepository = false;
	
	public SsoSessionRepositoryFilter() {}
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	if(isRepository){
    		this.ssoSessionRepositoryFilterPolicy.doFilterInternal(request, response, filterChain);
    	}else{
    		XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest)request);  
    		filterChain.doFilter(xssRequest, response); 
    	}
    }
    
    public void init(FilterConfig config) {
    	this.filterConfig = config;
    	this.isRepository = this.getIsRepository();
    	if(isRepository){
	    	SsoRedisOperationsSessionRepository ssoRedisOperationsSessionRepository = new SsoRedisOperationsSessionRepository();
	    	ssoSessionRepositoryFilterPolicy = new SsoSessionRepositoryFilterPolicy(ssoRedisOperationsSessionRepository,filterConfig.getServletContext());
	    	SsoHttpSessionStrategy ssoHttpSessionStrategy = new SsoCookieHttpSessionStrategy();
	    	ssoSessionRepositoryFilterPolicy.setHttpSessionStrategy(ssoHttpSessionStrategy);
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
