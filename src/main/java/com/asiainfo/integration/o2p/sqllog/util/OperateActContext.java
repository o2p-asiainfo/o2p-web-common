package com.asiainfo.integration.o2p.sqllog.util;

import com.ailk.eaap.o2p.sqllog.model.OperateActInfo;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;

/**
 * @ClassName: OperateActContext
 * @Description: 
 * @author zhengpeng
 * @date 2015-3-6 上午10:13:12
 *
 */
public class OperateActContext {
	
	private OperateActContext(){}
	
	private static ThreadLocal<OperateActInfo> actInfoLocal = new ThreadLocal<OperateActInfo>();
	private static ThreadLocal<UserRoleInfo> userLocal = new ThreadLocal<UserRoleInfo>();
	private static ThreadLocal<String> operateCodeFilterLocal = new ThreadLocal<String>();
	
	public static String getOperateCode(){
		return operateCodeFilterLocal.get();
	}
	
	public static void setOperateCode(String operateCode) {
		removeOperateCode();
		operateCodeFilterLocal.set(operateCode);
	}
	
	public static void removeOperateCode(){
		operateCodeFilterLocal.remove();
	}
	
	public static UserRoleInfo getUserInfo(){
		return userLocal.get();
	}
	
	public static void setUserInfo(UserRoleInfo UserRoleInfo) {
		userLocal.set(UserRoleInfo);
	}
	
	public static void removeUserInfo(){
		userLocal.remove();
	}
	
	public static OperateActInfo getActInfo() {
		return (OperateActInfo) actInfoLocal.get();
	}

	public static void setActInfo(OperateActInfo operateActInfo) {
		actInfoLocal.set(operateActInfo);
	}
	
	public static void removeActInfo(){
		actInfoLocal.remove();
	}

}
