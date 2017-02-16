package com.ailk.eaap.o2p.common.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.session.web.manage.SSOUserFactory;
import com.asiainfo.integration.o2p.web.bo.LoginUserInfo;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.util.StringUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName: SsoUserInterceptor
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-6 下午3:54:38
 *
 */
public class SsoUserInterceptor extends AbstractInterceptor{
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLog(this.getClass());
	
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		int result = 0;
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserRoleInfo ssoUserRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
		UserRoleInfo portalUserRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY);
		//超时
		if(ssoUserRoleInfo == null){
			result = 2;
			if(portalUserRoleInfo != null){
				result = 3;
			}
		}else{
			//判断用户是否重复登录和超出规定的时间
			String isFilterStr = WebPropertyUtil.getPropertyValue("is_sso_verify");
			//默认不验证, 或者为true
			if(!StringUtil.isEmpty(isFilterStr) && Boolean.valueOf(isFilterStr)){
		    	String sessionId = session.getId();
		    	LoginUserInfo loginUserInfo = SSOUserFactory.getDefaultSsoUserCache().getUser(ssoUserRoleInfo.getName());
		    	
		    	if(loginUserInfo != null){
		    		String loginSessionId = loginUserInfo.getSessionId();
		    		//重复登录
		    		if(!sessionId.equals(loginSessionId)){
		    			result = 1;
		    		//成功
		    		}else{
		    			result = 0;
		    		}
		    	//超时
		    	}else{
		    		result = 2;
		    	}
			}
		}
		if(result == 1){
			return "sso-error"; 
		}else if(result == 2){
			return "timeout-error"; 
		}else{
			return invocation.invoke();  
		}
	}
	
	
//	@Override
//	public String intercept(ActionInvocation invocation) throws Exception {
//		int result = 0;
//		String isO2pSso = WebPropertyUtil.getPropertyValue("is_o2p_sso");
//		//默认验证(is_o2p_sso不填)，或者为true
//		if(StringUtil.isEmpty(isO2pSso) || Boolean.valueOf(isO2pSso)){
//			HttpSession session = ServletActionContext.getRequest().getSession();
//			UserRoleInfo ssoUserRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
//			UserRoleInfo portalUserRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY);
//			//超时
//			if(ssoUserRoleInfo == null){
//				result = 2;
//				if(portalUserRoleInfo != null){
//					result = 3;
//				}
//			}else{
//				//判断用户是否重复登录和超出规定的时间
//				String isFilterStr = WebPropertyUtil.getPropertyValue("is_sso_verify");
//				//默认不验证, 或者为true
//				if(!StringUtil.isEmpty(isFilterStr) && Boolean.valueOf(isFilterStr)){
//			    	String sessionId = session.getId();
//			    	LoginUserInfo loginUserInfo = SSOUserFactory.getDefaultSsoUserCache().getUser(ssoUserRoleInfo.getName());
//			    	
//			    	if(loginUserInfo != null){
//			    		String loginSessionId = loginUserInfo.getSessionId();
//			    		//重复登录
//			    		if(!sessionId.equals(loginSessionId)){
//			    			result = 1;
//			    		//成功
//			    		}else{
//			    			result = 0;
//			    		}
//			    	//超时
//			    	}else{
//			    		result = 2;
//			    	}
//				}
//			}
//		}
//		if(result == 1){
//			return "sso-error"; 
//		}else if(result == 2){
//			return "timeout-error"; 
//		}else{
//			return invocation.invoke();  
//		}
//	}

}
