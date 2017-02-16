/** 
 * Project Name:o2p-web-common-tags 
 * File Name:AuthenticationUtil.java 
 * Package Name:com.asiainfo.integration.o2p.web.util 
 * Date:2016年3月10日下午11:23:36 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.web.util;  

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.ailk.eaap.op2.bo.hub.HubUrlParam;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/** 
 * ClassName:AuthenticationUtil <br/> 
 * Function: TODO put FUNCTION. <br/> 
 * Reason:   TODO put REASON. <br/> 
 * Date:     2016年3月10日 下午11:23:36 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class AuthenticationUtil {

	private static RestTemplate rest = new RestTemplate();
	public static String HUB_FLOW = WebPropertyUtil.getPropertyValue("hub_url");
	public static String SYSTEM_SERVICE_CODE="3950dc1a-5201-0010-ba3b-a9d9d2299a7d";
	public static String REGIST_ACCOUNT_SERVICE_CODE="f318b477-5201-0010-95be-0b5e8479e6d4";
	public static String PARTY_SERVICE_CODE="464edc1a-5201-0010-ba3b-a9d9d2299a7d";
	public static String RESET_PASSWORD_SERVICE_CODE="e54d1859-5201-0010-8d29-bb3bab474b19";
	public static String SAFE_LINK_SERVICE_CODE="646e9d25-5501-0010-9855-ad5529e8e8ec";
	public static String CHANGE_PASSWORD_SERVICE_CODE="a814e758-5201-0010-8d29-bb3bab474b19";
	public static String VERIFY_USER_NAME_SERVICE_CODE="9a79717d-5501-0010-b244-dd08683f86fc";

	public static JSONObject systemAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", SYSTEM_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("roleType", hubUrlParam.getRoleType());
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&roleType={roleType}",
				null, String.class,vars); 
	    
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static JSONObject registAccountAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", REGIST_ACCOUNT_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("token", hubUrlParam.getToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&token={token}&roleType={roleType}",
				null, String.class,vars); 
	    
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	
	public static JSONObject partyAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", PARTY_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&roleType={roleType}",
				null, String.class,vars); 
	    
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static JSONObject resetPasswordAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", RESET_PASSWORD_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("token", hubUrlParam.getToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&token={token}&roleType={roleType}",
				null, String.class,vars); 
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static JSONObject sendSafeLink(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", SAFE_LINK_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("token", hubUrlParam.getToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&token={token}&roleType={roleType}",
				null, String.class,vars); 
	    
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static JSONObject changePasswordAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", CHANGE_PASSWORD_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("token", hubUrlParam.getToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&token={token}&roleType={roleType}",
				null, String.class,vars); 
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	
	public static JSONObject verifyUserNameAuthenticate(HubUrlParam hubUrlParam){
		JSONObject retJson = null;
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("serviceCode", VERIFY_USER_NAME_SERVICE_CODE);
		vars.put("webHubParamsJson", hubUrlParam.getWebHubParamsJson());
		vars.put("tenant", hubUrlParam.getTenant());
		vars.put("appToken", hubUrlParam.getAppToken());
		vars.put("token", hubUrlParam.getToken());
		vars.put("roleType", hubUrlParam.getRoleType());
		
		
		String message = rest.postForObject(HUB_FLOW+"?servicecode={serviceCode}&WEB_HUB_PARAMS={webHubParamsJson}&tenant={tenant}&appToken={appToken}&token={token}&roleType={roleType}",
				null, String.class,vars); 
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static String generatedAppToken(Integer apphubTenantId){
		HubUrlParam hubUrlParam=new HubUrlParam();
		hubUrlParam.setRoleType(EAAPConstants.HUB_OPERATOR);
		hubUrlParam.setTenant(apphubTenantId.toString());
		com.alibaba.fastjson.JSONObject webHubParamsJsonObj = new com.alibaba.fastjson.JSONObject();
		webHubParamsJsonObj.put("userName", EAAPConstants.HUB_USERNAME);
		webHubParamsJsonObj.put("pwd", EAAPConstants.HUB_PASSWORD);
		hubUrlParam.setWebHubParamsJson(webHubParamsJsonObj);
		com.alibaba.fastjson.JSONObject getJson = systemAuthenticate(hubUrlParam);
		String appTokenStr = "";
		if("1".equals(getJson.get("hub_code").toString())){
			appTokenStr= getJson.get("data").toString();
		}
		
		return appTokenStr;
	}
	
	
}
