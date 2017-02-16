package com.asiainfo.integration.o2p.web.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ailk.eaap.op2.common.EAAPConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WorkRestUtils {

	private static RestTemplate rest = new RestTemplate();
	
	public static String WORK_FLOW = WebPropertyUtil.getPropertyValue("work_flow_pro_url");
	
	public static String WORK_FLOW_RESULT_OK = "0000";
	
	public static JSONObject startWorkflowAndVal(Object processKey,Object businessKey,Object userId,Object tenantId,Object varJson){
		JSONObject retJson = null;
		
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<String, Object>();
		requestBody.add("processKey", processKey);
		requestBody.add("businessKey", businessKey);
		requestBody.add("userId", userId);
		requestBody.add("tenantId",String.valueOf(tenantId));
		requestBody.add("varJson", varJson);
		
		String message = rest.postForObject(WORK_FLOW+"/process/startWorkflowAndVal",
				requestBody, String.class); 
	    
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return retJson;
	}
	
	public static String taskListByProcessId(String auditFlowId){
		return rest.getForObject(WORK_FLOW+"/task/taskListByProcessId/{processId}",String.class,auditFlowId);
	}
	
	public static String getActMsgInfo(String activityId){
		return rest.getForObject(WORK_FLOW+"/actInstanceByActivityId/{activity_Id}",String.class,activityId);
	}
	
	public static String getNextTach(String processId){
		String nextTachNameList=rest.getForObject(WORK_FLOW+"/getNextTachByProcessId/{process_Id}",String.class,processId);
		  return nextTachNameList;
	}
	
	public static String getActivityBeforeErrorAct(String processId){
		String actBeforeErrorActId=rest.getForObject(WORK_FLOW+"/activityBeforeErrorAct/{process_Id}",String.class,processId);
		  return actBeforeErrorActId;
	}
	
	public static String completeTask(Object taskId,Object varJson){
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("taskId", taskId);
		taskVariables.put("varJson", varJson);
		return rest.postForObject(WORK_FLOW+"/task/completeTask?taskId={taskId}&varJson={varJson}",
	    		null, String.class,taskVariables);
	}
	
	public static Boolean isExistTaskByTaskId(String taskId){
		RestTemplate rest = new RestTemplate();
		String message = rest.getForObject(WORK_FLOW+"/task/isExitTask/{taskId}",
	    		String.class,taskId);
		JSONObject retJson = new JSONObject();
		if(!StringUtils.isEmpty(message)){
			retJson = JSON.parseObject(message);
		}
		return "0000".equals(retJson.getString("code"));
	}
	
	public static JSONObject signalTask(Map<String, Object> variables){
		String message = rest.postForObject(WORK_FLOW+"/task/signalTask?processInstanceId={processInstanceId}&activityId={activityId}&varJson={varJson}",
	    		null, String.class,variables);
		return JSON.parseObject(message);
	}
	
	public static JSONObject signalTask(String processInstanceId,String activityId,Map<String, Object> variables){
		variables.put("processInstanceId", processInstanceId);
		variables.put("activityId", activityId);
		
		return JSON.parseObject(rest.postForObject(WORK_FLOW+"/task/signalTask?processInstanceId={processInstanceId}&activityId={activityId}&varJson={varJson}",
	    		null, String.class,variables));
	}
	
	/**
	 * 	Offer下发流程启动
	 * @param prodOfferId
	 * @param localId
	 * @return
	 */
	public static JSONObject syncLocalProdOffer(String prodOfferId,String localId){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("prodOfferId", prodOfferId);
		variables.put("localId", localId);
		variables.put("sendAction", EAAPConstants.CLOUD_LOCAL_AUDIT_INTERFACE_BUSICODE_OFFER_MODALE_OFFER);
		
		return JSON.parseObject(rest.postForObject(WORK_FLOW+"/cloudToLocal/syncLocalProdOffer?prodOfferId={prodOfferId}&localId={localId}&sendAction={sendAction}",
	    		null, String.class,variables));
	}
	
	/**
	 * Offer 流程重发流程启动
	 * @param prodOfferId
	 * @param localId
	 * @param sendAction
	 * @return
	 */
	public static JSONObject syncLocalProdOffer(String prodOfferId,String localId,String sendAction){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("prodOfferId", prodOfferId);
		variables.put("localId", localId);
		variables.put("sendAction", sendAction);
		
		return JSON.parseObject(rest.postForObject(WORK_FLOW+"/cloudToLocal/syncLocalProdOffer?prodOfferId={prodOfferId}&localId={localId}&sendAction={sendAction}",
	    		null, String.class,variables));
	}
	
	public static JSONObject signalCloudTask(String reqMsg){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("xml", reqMsg);
		return JSON.parseObject(rest.postForObject(WORK_FLOW+"/task/signalCloudTask?xml={xml}",null,String.class,variables));
	}
	
	/**
	 * 
	 * @param objectId
	 * @param objectType
	 * @param localId
	 * @param result
	 * @param processKey
	 * @return
	 */
	public static Boolean qrySyncResult(String objectId,String objectType,String localId,String result,String processKey){
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<String, Object>();
		requestBody.add("objectId", objectId);
		requestBody.add("objectType", objectType); 
		requestBody.add("localId", localId);
		requestBody.add("result", result);
		requestBody.add("processKey", processKey);
		
		return WebConstants.RESULT_OK.equals(JSON.parseObject(rest.postForObject(WORK_FLOW+"/cloudToLocal/qrySyncResult",requestBody, String.class)).get(WebConstants.RETURN_CODE)); 
	}
	
	public static void saveExceptionLog(String objectId,String processBusinessKey,String destination,String exceptionDetails,String activitiId){
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<String, Object>();
		requestBody.add("objectId", objectId);
		requestBody.add("processBusinessKey", processBusinessKey); 
		requestBody.add("destination", destination);
		requestBody.add("exceptionDetails", exceptionDetails);
		requestBody.add("activitiId", activitiId);
		
		rest.postForObject(WORK_FLOW+"/cloudToLocal/saveExceptionLog",requestBody, String.class); 
	}
}
