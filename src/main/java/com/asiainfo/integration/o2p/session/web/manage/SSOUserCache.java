package com.asiainfo.integration.o2p.session.web.manage;

import java.util.Map;

import com.asiainfo.integration.o2p.web.bo.LoginUserInfo;

/**
 * @ClassName: SSOUserCache
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-1 下午5:52:03
 *
 */
public interface SSOUserCache {
	
	public void addUser(String userName,String sessionId);
	
	public LoginUserInfo getUser(String userName);
		
	public void removeUser(String userName);
	
	public Map<Object,Object> getUserVaildMap();
	
	public void removeKey(String key);

}
