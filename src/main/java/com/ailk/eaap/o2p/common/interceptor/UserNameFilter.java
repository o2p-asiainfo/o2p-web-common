package com.ailk.eaap.o2p.common.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.ailk.eaap.o2p.common.spring.config.ZKCfgCacheHolder;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.web.util.PropertyUtil;
import com.linkage.rainbow.util.StringUtil;

/**
 * @ClassName: UserNameFilter
 * @Description: 
 * @author zhengpeng
 * @date 2015-12-2 下午3:00:12
 *
 */
public class UserNameFilter implements Filter{

	private static Logger log = Logger.getLog(UserNameFilter.class);
	@Override
	public void destroy() {
	}
	
	private String getPropertyValue(String propertyKey){
		Object propertyValueObj = ZKCfgCacheHolder.PROP_ITEMS.get(propertyKey);
		String propertyValue = "";
		if(propertyValueObj != null){
			propertyValue = String.valueOf(propertyValueObj);
		}else{
			propertyValue = PropertyUtil.getValueByProCode(propertyKey);
		}
		return propertyValue;
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
	            throw new ServletException("UserNameFilter just supports HTTP requests");
	    }
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest)request);
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper((HttpServletResponse)response);
		boolean isPass = true;
		String isFilterStr = this.getPropertyValue(EAAPConstants.WEB_COOKIE_USERNAME_FILTER); 
		if(!StringUtil.isEmpty(isFilterStr) && Boolean.valueOf(isFilterStr)){
			String userName = this.getUserNameForCookie(requestWrapper);
			log.debug("################# getUserName:" + userName); 
			if(StringUtil.isEmpty(userName)){
				isPass = false;
			}else{
				isPass = true;
			}
		}
		if(isPass){
			chain.doFilter(request, response);  
		}else{
			String errorJsp = this.getPropertyValue("o2p_conf_error_url");
			responseWrapper.setHeader("Cache-Control", "no-store");  
			responseWrapper.setDateHeader("Expires", 0);  
			responseWrapper.setHeader("Prama", "no-cache");  
			responseWrapper.sendRedirect(errorJsp);  
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	/**
	 * 从cookie里获取UserName
	 */
	private String getUserNameForCookie(HttpServletRequest request){
		String userName = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
			    if(EAAPConstants.O2P_USER_NAME.equals(cookie.getName())){
			    	if(!StringUtil.isEmpty(cookie.getValue())){
			    		userName = cookie.getValue();
					}
			    }
			}
		}
		return userName; 
	}

}
