/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:RTIEventUtils.java 
 * Package Name:com.asiainfo.integration.o2p.web.util 
 * Date:2016年8月5日下午5:19:22 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.web.util;  

import java.util.Map;

import com.ailk.eaap.op2.bo.RtiRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

/** 
 * ClassName:RTIEventUtils <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年8月5日 下午5:19:22 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class RTIEventUtils {

	private static final Logger log = Logger.getLog(PushCInvokeUtils.class);
	public static String START_RTI_SUBSCRIBE_URL =WebPropertyUtil.getPropertyValue("start_rti_subscribe_url");
	public static String STOP_RTI_SUBSCRIBE_URL =WebPropertyUtil.getPropertyValue("stop_rti_subscribe_url");
	public static String  CREATE_RTI_SUBSCRIBE_URL=WebPropertyUtil.getPropertyValue("create_rti_subscribe_url");
	public static final Integer RTI_SEND_TIME_OUT = 130;
	
	public static String startRtiSubscribe(Map<String,String> paramMap){
		JSONObject data = new JSONObject();
		JSONObject health = new JSONObject();
		health.put("code",1);
		data.put("health", health);
		String eventId=paramMap.get("eventId");
		String localName=paramMap.get("localName");
		JSONObject requestJson = new JSONObject();
		requestJson.put("eventId", eventId);
		requestJson.put("subscriberId", 1);
		
		String requestStr=requestJson.toJSONString();
		StringBuilder inMsg = new StringBuilder("");
		inMsg.append(requestStr);
		String retMsg = HttpClientUtil.sendJsonRequest(START_RTI_SUBSCRIBE_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, RTI_SEND_TIME_OUT, null,
				null, null, null, null,localName);
		log.debug(retMsg);
		if(retMsg.contains("200")){//审核成功
			data.put("result", 1);   //1表示同步成功
		}else{
			data.put("result", 0);//0 失败 
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	   return data.toJSONString();
	}
	
	
	public static String stopRtiSubscribe(Map<String,String> paramMap){
		JSONObject data = new JSONObject();
		JSONObject health = new JSONObject();
		health.put("code",1);
		data.put("health", health);
		String eventId=paramMap.get("eventId");
		String localName=paramMap.get("localName");
		JSONObject requestJson = new JSONObject();
		requestJson.put("eventId", eventId);
		requestJson.put("subscriberId", 1);
		
		String requestStr=requestJson.toJSONString();
		StringBuilder inMsg = new StringBuilder("");
		inMsg.append(requestStr);
		String retMsg = HttpClientUtil.sendJsonRequest(STOP_RTI_SUBSCRIBE_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, RTI_SEND_TIME_OUT, null,
				null, null, null, null,localName);
		log.debug(retMsg);
		if(retMsg.contains("200")){//审核成功
			data.put("result", 1);   //1表示同步成功
		}else{
			data.put("result", 0);//0 失败 
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
			
		}
		
	   return data.toJSONString();
	}
	
	public static String createRtiMarketingRule(RtiRule rtiRule){
		JSONObject health = new JSONObject();
		JSONObject data = new JSONObject();
		health.put("code",1);
		data.put("health", health);
		
		String requestStr=rtiRule.getRtiRuleContent();
		StringBuilder inMsg = new StringBuilder("");
		inMsg.append(requestStr);
		String retMsg = HttpClientUtil.sendRequest(CREATE_RTI_SUBSCRIBE_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, RTI_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		
		if(retMsg.indexOf("200") > 0){//审核成功
			JSONObject createRetJson = new JSONObject();
			createRetJson = JSON.parseObject(retMsg);
			data.put("data", createRetJson);
		}else{
			data.put("data", null);
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
			
		}
		
	  return data.toJSONString();
	}
}
