package com.ailk.eaap.o2p.common.interceptor;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.util.StringUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName: UserNameInterceptor
 * @Description: 
 * @author zhengpeng
 * @date 2015-2-5 上午10:53:22
 *
 */
public class UserNameInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLog(this.getClass());

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String isFilterStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_COOKIE_USERNAME_FILTER);
		boolean isPass = true;
		if(!StringUtil.isEmpty(isFilterStr) && Boolean.valueOf(isFilterStr)){
			String userName = this.getUserNameForCookie();
			log.debug("################# getUserName:" + userName); 
			if(StringUtil.isEmpty(userName)){
				isPass = false;
			}else{
				isPass = true;
			}
		}
		if(isPass){
			return invocation.invoke();  
		}else{
			return "exception-error"; 
		}
	}
	
	/**
	 * 从cookie里获取UserName
	 */
	private String getUserNameForCookie(){
		String userName = "";
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				log.debug("######## cookie:" + cookie.getName() + "|| value:" + cookie.getValue());
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
