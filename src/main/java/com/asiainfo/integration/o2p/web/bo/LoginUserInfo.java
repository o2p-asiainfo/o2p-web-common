package com.asiainfo.integration.o2p.web.bo;

/**
 * @ClassName: LoginInfo
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-1 下午2:34:19
 *
 */
public class LoginUserInfo {
	
	private String userName;
	private long loginTime;
	private String sessionId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


}
