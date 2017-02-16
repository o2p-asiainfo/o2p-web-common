/** 
 * Project Name:o2p-web-common 
 * File Name:PushCInvoke.java 
 * Package Name:com.asiainfo.integration.o2p.web.util 
 * Date:2016年2月24日下午3:14:59 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.web.util;  

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;








import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;




import org.apache.commons.lang.StringUtils;

import com.ailk.eaap.o2p.common.security.SecurityUtil;
import com.ailk.eaap.op2.bo.App;
import com.ailk.eaap.op2.bo.Org;
import com.ailk.eaap.op2.bo.ProdOffer;
import com.ailk.eaap.op2.bo.Product;
import com.ailk.eaap.op2.bo.Component;
import com.ailk.eaap.op2.bo.pushc.CiInfo;
import com.ailk.eaap.op2.bo.pushc.EmailRequestMessage;
import com.ailk.eaap.op2.bo.pushc.PushBatchEmailByBusinessTypeAndContentRequest;
import com.ailk.eaap.op2.bo.pushc.PushBatchEmailByBusinessTypeAndContentRequestBody;
import com.ailk.eaap.op2.bo.pushc.PushBatchSmsByBusinessTypeAndSmsContentRequest;
import com.ailk.eaap.op2.bo.pushc.PushBatchSmsByBusinessTypeAndSmsContentRequestBody;
import com.ailk.eaap.op2.bo.pushc.PushEmailByContentRequest;
import com.ailk.eaap.op2.bo.pushc.PushEmailByContentRequestBody;
import com.ailk.eaap.op2.bo.pushc.PushSmsByContentRequest;
import com.ailk.eaap.op2.bo.pushc.PushSmsByContentRequestBody;
import com.ailk.eaap.op2.bo.pushc.PushTargetList;
import com.ailk.eaap.op2.bo.pushc.RequestHeader;
import com.ailk.eaap.op2.bo.pushc.SmsRequestMessage;
import com.ailk.eaap.op2.bo.pushc.SoapBody;
import com.ailk.eaap.op2.bo.pushc.SoapHeader;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

/** 
 * ClassName:PushCInvoke <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年2月24日 下午3:14:59 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class PushCInvokeUtils {
	private static final Logger log = Logger.getLog(PushCInvokeUtils.class);
	public static String PUSHC_URL =WebPropertyUtil.getPropertyValue("pushc_url");
	public static String PUSHC_APPKEY=WebPropertyUtil.getPropertyValue("pushc_appkey");
	public static final Integer PUSHC_SEND_TIME_OUT = 130;
 
	
	public static Boolean pushEmailByForgetPwdContentForHttp(com.asiainfo.integration.o2p.web.bo.Org orgWeb,String mailTitle,String messageContent,String opId){
		Boolean sentResult = Boolean.FALSE;
		
		EmailRequestMessage emailRequestMessage=new EmailRequestMessage();
		RequestHeader requestHeader=new RequestHeader();
		requestHeader.setAppKey(PUSHC_APPKEY);
		requestHeader.setTransactionID(genTransactionID());
		requestHeader.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		requestHeader.setTenantId("21");//
		requestHeader.setAcceptChannelType("O2P101");
		requestHeader.setAcceptStaffId("21O2P101");
//		requestHeader.setAccessToken(AuthenticationUtil.generatedAppToken());
		SoapHeader soapHeader=new SoapHeader();
		soapHeader.setRequestHeader(requestHeader);
		emailRequestMessage.setHeader(soapHeader);
		
		PushEmailByContentRequest pushEmailByContentRequest=new PushEmailByContentRequest();
		PushEmailByContentRequestBody pushEmailByContentRequestBody=new PushEmailByContentRequestBody();
		pushEmailByContentRequestBody.setNotificationCode("TM_TOPIC_TICKET_TIME_OUT_NOTIFICATION");//
		pushEmailByContentRequestBody.setEmailSubject(mailTitle);
		pushEmailByContentRequestBody.setTargetAddress(orgWeb.getEmail());
		pushEmailByContentRequestBody.setEmailContent(messageContent);
		pushEmailByContentRequestBody.setOpId(Long.valueOf(opId));
		pushEmailByContentRequestBody.setStrategy(null);
		CiInfo ciInfo=new CiInfo();
		ciInfo.setSrcSysId("1000010005");
		ciInfo.setChannelCode("");
		ciInfo.setSrcBusiType("");
	    ciInfo.setSrcInteractionEntityId(orgWeb.getOrgId().toString());
		pushEmailByContentRequestBody.setCiInfo(ciInfo);
		pushEmailByContentRequest.setRequestBody(pushEmailByContentRequestBody);
		SoapBody soapBody=new SoapBody();
		soapBody.setPushEmailByContentRequestBody(pushEmailByContentRequest);
		emailRequestMessage.setBody(soapBody);
		
		//生成xml报文
	    JAXBContext ct;
	    Marshaller marshaller;
	    StringWriter inMsg = null;
		try {
			ct = JAXBContext.newInstance(EmailRequestMessage.class);
			marshaller=ct.createMarshaller();
			inMsg= new StringWriter();
			marshaller.marshal(emailRequestMessage, inMsg);
			log.debug(inMsg.toString());
		} catch (JAXBException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}
		String retMsg = HttpClientUtil.sendRequest(PUSHC_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, PUSHC_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		if(retMsg.indexOf(">0000<") > 0){//审核成功
			sentResult = Boolean.TRUE;
		}else{
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send Email,target email address:"+orgWeb.getEmail()+"fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	
	   return sentResult;
    }
	
	
	
	public static Boolean pushSmsByContentForHttp(Org org,Object object,String messageContent,String opId){
		Boolean sentResult = Boolean.FALSE;
		
		SmsRequestMessage smsRequestMessage=new SmsRequestMessage();
		RequestHeader requestHeader=new RequestHeader();
		requestHeader.setAppKey(PUSHC_APPKEY);
		requestHeader.setTransactionID(genTransactionID());
		requestHeader.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		requestHeader.setTenantId("21");//
		requestHeader.setAcceptChannelType("O2P101");
		requestHeader.setAcceptStaffId("21O2P101");
//		requestHeader.setAccessToken(AuthenticationUtil.generatedAppToken());
		SoapHeader soapHeader=new SoapHeader();
		soapHeader.setRequestHeader(requestHeader);
		smsRequestMessage.setHeader(soapHeader);
		
		PushSmsByContentRequest pushSmsByContentRequest=new PushSmsByContentRequest();
		PushSmsByContentRequestBody pushSmsByContentRequestBody=new PushSmsByContentRequestBody();
		pushSmsByContentRequestBody.setNotificationCode("TM_TOPIC_TICKET_TIME_OUT_NOTIFICATION");//
		pushSmsByContentRequestBody.setPort("");
		pushSmsByContentRequestBody.setTargetAddress(org.getTelephone());
		pushSmsByContentRequestBody.setSmsContent(messageContent);
		pushSmsByContentRequestBody.setOpId(Long.valueOf(opId));
		pushSmsByContentRequestBody.setStrategy(null);
		CiInfo ciInfo=new CiInfo();
		ciInfo.setSrcSysId("1000010005");
		ciInfo.setChannelCode("");
		ciInfo.setSrcBusiType("");
	    if(object==null){
	    	ciInfo.setSrcInteractionEntityId(org.getOrgId().toString());
	    }else if (object instanceof Product){
	    	Product product=(Product) object;
	    	ciInfo.setSrcInteractionEntityId(product.getProductId().toString());
	    }else if(object instanceof ProdOffer){
	    	ProdOffer prodOffer=(ProdOffer) object;
	    	ciInfo.setSrcInteractionEntityId(prodOffer.getProdOfferId().toString());
	    }else if(object instanceof Component){
	    	Component component=(Component) object;
	    	ciInfo.setSrcInteractionEntityId(component.getComponentId().toString());
	    }else if(object instanceof App){
	    	App app=(App) object;
	    	ciInfo.setSrcInteractionEntityId(app.getAppId().toString());
	    }
		pushSmsByContentRequestBody.setCiInfo(ciInfo);
		pushSmsByContentRequest.setRequestBody(pushSmsByContentRequestBody);
		SoapBody soapBody=new SoapBody();
		soapBody.setPushSmsByContentRequestBody(pushSmsByContentRequest);
		smsRequestMessage.setBody(soapBody);
		//生成xml报文
	    JAXBContext ct;
	    Marshaller marshaller;
	    StringWriter inMsg = null;
		try {
			ct = JAXBContext.newInstance(SmsRequestMessage.class);
			marshaller=ct.createMarshaller();
			inMsg= new StringWriter();
			marshaller.marshal(smsRequestMessage, inMsg);
			log.debug(inMsg.toString());
		} catch (JAXBException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}
		String retMsg = HttpClientUtil.sendRequest(PUSHC_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, PUSHC_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		if(retMsg.indexOf(">0000<") > 0){//审核成功
			sentResult = Boolean.TRUE;
		}else{
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send SMS,target number:"+org.getTelephone()+"fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	
	   return sentResult;
    }
	
	
	public static Boolean pushEmailByContentForHttp(Org org,Object object,String messageContent,String opId){
		Boolean sentResult = Boolean.FALSE;
		EmailRequestMessage emailRequestMessage=new EmailRequestMessage();
		RequestHeader requestHeader=new RequestHeader();
		requestHeader.setAppKey(PUSHC_APPKEY);
		requestHeader.setTransactionID(genTransactionID());
		requestHeader.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		requestHeader.setTenantId("21");//
		requestHeader.setAcceptChannelType("O2P101");
		requestHeader.setAcceptStaffId("21O2P101");
//		requestHeader.setAccessToken(AuthenticationUtil.generatedAppToken());
		SoapHeader soapHeader=new SoapHeader();
		soapHeader.setRequestHeader(requestHeader);
		emailRequestMessage.setHeader(soapHeader);
		
		PushEmailByContentRequest pushEmailByContentRequest=new PushEmailByContentRequest();
		PushEmailByContentRequestBody pushEmailByContentRequestBody=new PushEmailByContentRequestBody();
		pushEmailByContentRequestBody.setNotificationCode("TM_TOPIC_TICKET_TIME_OUT_NOTIFICATION");//
		pushEmailByContentRequestBody.setEmailSubject("[Open Operational Platform] Auditing Result Mail");
		pushEmailByContentRequestBody.setTargetAddress(org.getEmail());
		pushEmailByContentRequestBody.setEmailContent(messageContent);
		pushEmailByContentRequestBody.setOpId(Long.valueOf(opId));
		pushEmailByContentRequestBody.setStrategy(null);
		CiInfo ciInfo=new CiInfo();
		ciInfo.setSrcSysId("1000010005");
		ciInfo.setChannelCode("");
		ciInfo.setSrcBusiType("");
		if(object==null){
	    	ciInfo.setSrcInteractionEntityId(org.getOrgId().toString());
	    }else if (object instanceof Product){
	    	Product product=(Product) object;
	    	ciInfo.setSrcInteractionEntityId(product.getProductId().toString());
	    }else if(object instanceof ProdOffer){
	    	ProdOffer prodOffer=(ProdOffer) object;
	    	ciInfo.setSrcInteractionEntityId(prodOffer.getProdOfferId().toString());
	    }else if(object instanceof Component){
	    	Component component=(Component) object;
	    	ciInfo.setSrcInteractionEntityId(component.getComponentId().toString());
	    }else if(object instanceof App){
	    	App app=(App) object;
	    	ciInfo.setSrcInteractionEntityId(app.getAppId().toString());
	    }
		pushEmailByContentRequestBody.setCiInfo(ciInfo);
		pushEmailByContentRequest.setRequestBody(pushEmailByContentRequestBody);
		SoapBody soapBody=new SoapBody();
		soapBody.setPushEmailByContentRequestBody(pushEmailByContentRequest);
		emailRequestMessage.setBody(soapBody);
		
		//生成xml报文
	    JAXBContext ct;
	    Marshaller marshaller;
	    StringWriter inMsg = null;
		try {
			ct = JAXBContext.newInstance(EmailRequestMessage.class);
			marshaller=ct.createMarshaller();
			inMsg= new StringWriter();
			marshaller.marshal(emailRequestMessage, inMsg);
			log.debug(inMsg.toString());
		} catch (JAXBException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}
		String retMsg = HttpClientUtil.sendRequest(PUSHC_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, PUSHC_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		if(retMsg.indexOf(">0000<") > 0){//审核成功
			sentResult = Boolean.TRUE;
		}else{
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send Email,target email address:"+org.getEmail()+"fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	
	   return sentResult;
    }
	
	public static void pushCInvokeByType(Org org,Object object,String messageContent,String opId,String pushMessageType){
		if("1,".equals(pushMessageType)){
			pushEmailByContentForHttp(org,object,messageContent,opId);
		}else if("2,".equals(pushMessageType)){
			pushSmsByContentForHttp(org,object,messageContent,opId);
		}else if("1,2,".equals(pushMessageType)){
			pushSmsByContentForHttp(org,object,messageContent,opId);
			pushEmailByContentForHttp(org,object,messageContent,opId);
		}
	}
	
	public static void pushCBatchInvokeByType(Map paramMap,String messageContent,String pushMessageType){
		if("1,".equals(pushMessageType)){
			List<PushTargetList> pushTargetInfoParamList=(List) paramMap.get("pushTargetEmailInfoList");
			paramMap.put("pushTargetInfoList",pushTargetInfoParamList);
			pushBatchEmailByBusinessTypeAndContent( paramMap, messageContent);
		}else if("2,".equals(pushMessageType)){
			List<PushTargetList> pushTargetInfoParamList=(List) paramMap.get("pushTargetSmsList");
			paramMap.put("pushTargetInfoList",pushTargetInfoParamList);
			pushBatchSmsByBusinessTypeAndSmsContentRequest(paramMap,messageContent);
		}else if("1,2,".equals(pushMessageType)){
			List<PushTargetList> pushTargetInfoParamList=(List) paramMap.get("pushTargetEmailInfoList");
			paramMap.put("pushTargetInfoList",pushTargetInfoParamList);
			pushBatchEmailByBusinessTypeAndContent( paramMap, messageContent);
			List<PushTargetList> pushTargetInfoSMSParamList=(List) paramMap.get("pushTargetSmsList");
			paramMap.remove("pushTargetInfoList");
			paramMap.put("pushTargetInfoList",pushTargetInfoSMSParamList);
			pushBatchSmsByBusinessTypeAndSmsContentRequest(paramMap,messageContent);
		}
	}
	
	
	public static Boolean pushBatchEmailByBusinessTypeAndContent(Map paramMap,String messageContent){
		Boolean sentResult = Boolean.FALSE;
		Map busiDataInfoMap=(Map) paramMap.get("busiDataInfoMap");
		String tenantId=busiDataInfoMap.get("TenantId").toString();
		String acceptChannelType=busiDataInfoMap.get("AcceptChannelType").toString();
		String acceptStaffId=busiDataInfoMap.get("AcceptStaffId").toString();
		String appKey=busiDataInfoMap.get("AppKey").toString();
		String businessType=busiDataInfoMap.get("BusinessType").toString();
		String opId=null;
		String mailTitle="";
		if(paramMap.containsKey("mailTitle")){
			mailTitle=paramMap.get("mailTitle").toString();
		}
		if(paramMap.containsKey("opId")){
			 opId=paramMap.get("opId").toString();
		}
		String srcInteractionEntityId=paramMap.get("srcInteractionEntityId").toString();
		List<PushTargetList> pushTargetInfoParamList=(List) paramMap.get("pushTargetInfoList");
		
		EmailRequestMessage emailRequestMessage=new EmailRequestMessage();
		RequestHeader requestHeader=new RequestHeader();
		requestHeader.setAppKey(appKey);
		requestHeader.setTransactionID(genTransactionID());
		requestHeader.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		requestHeader.setTenantId(tenantId);
		requestHeader.setAcceptChannelType(acceptChannelType);
		requestHeader.setAcceptStaffId(acceptStaffId);
		SoapHeader soapHeader=new SoapHeader();
		soapHeader.setRequestHeader(requestHeader);
		emailRequestMessage.setHeader(soapHeader);
		
		PushBatchEmailByBusinessTypeAndContentRequest pushBatchEmailByBusinessTypeAndContentRequest=new PushBatchEmailByBusinessTypeAndContentRequest();
		PushBatchEmailByBusinessTypeAndContentRequestBody pushBatchEmailByBusinessTypeAndContentRequestBody=new PushBatchEmailByBusinessTypeAndContentRequestBody();
		pushBatchEmailByBusinessTypeAndContentRequestBody.setBusinessType(Long.valueOf(businessType));
		if(!StringUtils.isEmpty(mailTitle)){
			pushBatchEmailByBusinessTypeAndContentRequestBody.setEmailSubject(mailTitle);
		}else{
			pushBatchEmailByBusinessTypeAndContentRequestBody.setEmailSubject("[Open Operational Platform] Auditing Notice Mail");
		}
		pushBatchEmailByBusinessTypeAndContentRequestBody.setEmailContent(messageContent);
		if(opId!=null){
			pushBatchEmailByBusinessTypeAndContentRequestBody.setOpId(Long.valueOf(opId));
		}
		pushBatchEmailByBusinessTypeAndContentRequestBody.setStrategy(null);
		CiInfo ciInfo=new CiInfo();
		ciInfo.setSrcSysId("1000010005");
		ciInfo.setChannelCode("");
		ciInfo.setSrcBusiType("");
	    ciInfo.setSrcInteractionEntityId(srcInteractionEntityId);
		pushBatchEmailByBusinessTypeAndContentRequestBody.setCiInfo(ciInfo);
		
		List<PushTargetList> pushTargetInfoList=new ArrayList<PushTargetList>();
		for(PushTargetList pushTargetInfoParam:pushTargetInfoParamList){
			PushTargetList pushTargetInfo=new PushTargetList();
			pushTargetInfo.setTargetType(pushTargetInfoParam.getTargetType());
			pushTargetInfo.setTargetId(pushTargetInfoParam.getTargetId());
			pushTargetInfo.setTargeAddress(pushTargetInfoParam.getTargeAddress());
			pushTargetInfoList.add(pushTargetInfo);
		}
		pushBatchEmailByBusinessTypeAndContentRequestBody.setPushTargetList(pushTargetInfoList);
		pushBatchEmailByBusinessTypeAndContentRequest.setRequestBody(pushBatchEmailByBusinessTypeAndContentRequestBody);
		
		SoapBody soapBody=new SoapBody();
		soapBody.setPushBatchEmailByBusinessTypeAndContentRequestBody(pushBatchEmailByBusinessTypeAndContentRequest);
		emailRequestMessage.setBody(soapBody);
		//生成xml报文
	    JAXBContext ct;
	    Marshaller marshaller;
	    StringWriter inMsg = null;
		try {
			ct = JAXBContext.newInstance(EmailRequestMessage.class);
			marshaller=ct.createMarshaller();
			inMsg= new StringWriter();
			marshaller.marshal(emailRequestMessage, inMsg);
			log.debug(inMsg.toString());
		} catch (JAXBException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}
		String retMsg = HttpClientUtil.sendRequest(PUSHC_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, PUSHC_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		if(retMsg.indexOf(">0000<") > 0){//审核成功
			sentResult = Boolean.TRUE;
		}else{
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send Email fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	
	   return sentResult;
		
	}
	
	
	public static Boolean pushBatchSmsByBusinessTypeAndSmsContentRequest(Map paramMap,String messageContent){
		Boolean sentResult = Boolean.FALSE;
		Map busiDataInfoMap=(Map) paramMap.get("busiDataInfoMap");
		String tenantId=busiDataInfoMap.get("TenantId").toString();
		String acceptChannelType=busiDataInfoMap.get("AcceptChannelType").toString();
		String acceptStaffId=busiDataInfoMap.get("AcceptStaffId").toString();
		String appKey=busiDataInfoMap.get("AppKey").toString();
		String businessType=busiDataInfoMap.get("BusinessType").toString();
		String opId=null;
		if(paramMap.containsKey("opId")){
			 opId=paramMap.get("opId").toString();
		}
		String srcInteractionEntityId=paramMap.get("srcInteractionEntityId").toString();
		List<PushTargetList> pushTargetInfoParamList=(List) paramMap.get("pushTargetInfoList");
		
		SmsRequestMessage smsRequestMessage=new SmsRequestMessage();
		RequestHeader requestHeader=new RequestHeader();
		requestHeader.setAppKey(appKey);
		requestHeader.setTransactionID(genTransactionID());
		requestHeader.setReqTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		requestHeader.setTenantId(tenantId);
		requestHeader.setAcceptChannelType(acceptChannelType);
		requestHeader.setAcceptStaffId(acceptStaffId);
		SoapHeader soapHeader=new SoapHeader();
		soapHeader.setRequestHeader(requestHeader);
		smsRequestMessage.setHeader(soapHeader);
		
		PushBatchSmsByBusinessTypeAndSmsContentRequest pushBatchSmsByBusinessTypeAndSmsContentRequest=new PushBatchSmsByBusinessTypeAndSmsContentRequest();
		PushBatchSmsByBusinessTypeAndSmsContentRequestBody pushBatchSmsByBusinessTypeAndSmsContentRequestBody=new PushBatchSmsByBusinessTypeAndSmsContentRequestBody();
		pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setBusinessType(Long.valueOf(businessType));
		pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setSmsContent(messageContent);
		if(opId!=null){
			pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setOpId(Long.valueOf(opId));
		}
		pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setStrategy(null);
		CiInfo ciInfo=new CiInfo();
		ciInfo.setSrcSysId("1000010005");
		ciInfo.setChannelCode("");
		ciInfo.setSrcBusiType("");
	    ciInfo.setSrcInteractionEntityId(srcInteractionEntityId);
	    pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setCiInfo(ciInfo);
		
		List<PushTargetList> pushTargetInfoList=new ArrayList<PushTargetList>();
		for(PushTargetList pushTargetInfoParam:pushTargetInfoParamList){
			PushTargetList pushTargetInfo=new PushTargetList();
			pushTargetInfo.setTargetType(pushTargetInfoParam.getTargetType());//1：客户id 2：操作员id
			pushTargetInfo.setTargetId(pushTargetInfoParam.getTargetId());
			pushTargetInfo.setTargeAddress(pushTargetInfoParam.getTargeAddress());
			pushTargetInfoList.add(pushTargetInfo);
		}
		pushBatchSmsByBusinessTypeAndSmsContentRequestBody.setPushTargetList(pushTargetInfoList);
		pushBatchSmsByBusinessTypeAndSmsContentRequest.setRequestBody(pushBatchSmsByBusinessTypeAndSmsContentRequestBody);
		
		SoapBody soapBody=new SoapBody();
		soapBody.setPushBatchSmsByBusinessTypeAndSmsContentRequestBody(pushBatchSmsByBusinessTypeAndSmsContentRequest);
		smsRequestMessage.setBody(soapBody);
		//生成xml报文
	    JAXBContext ct;
	    Marshaller marshaller;
	    StringWriter inMsg = null;
		try {
			ct = JAXBContext.newInstance(SmsRequestMessage.class);
			marshaller=ct.createMarshaller();
			inMsg= new StringWriter();
			marshaller.marshal(smsRequestMessage, inMsg);
			log.debug(inMsg.toString());
		} catch (JAXBException e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}
		String retMsg = HttpClientUtil.sendRequest(PUSHC_URL, 
				inMsg.toString(), HttpClientUtil.POST_METHOD, PUSHC_SEND_TIME_OUT, null,
				null, null, null, null);
		log.debug(retMsg);
		if(retMsg.indexOf(">0000<") > 0){//审核成功
			sentResult = Boolean.TRUE;
		}else{
			log.error(LogModel.EVENT_APP_EXCPT,
					new BusinessException(ExceptionCommon.WebExceptionCode,"Send Sms fail!"+retMsg, null));
			throw new BusinessException(ExceptionCommon.WebExceptionCode,retMsg, null);
		}
	
	   return sentResult;
	}
	
	public static String decryInvoke(String encryString) throws Exception{
		 //解密操作
		String newString = "";
		if( !"".equals(encryString)){
			try{
				newString = SecurityUtil.getInstance().decryMsg(encryString);
				if(null == newString || "".equals(newString)){
					newString = encryString;
				}
			} catch(Exception e){
				newString = encryString;
			}
		}
		return newString;
	}
	
	public static String genTransactionID() {
		String orderNum = new Date().hashCode() + "";
		String lpad = lpad(orderNum);
		return "1000010005"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + lpad;
	}
	
	public static String lpad(String number) {
		String f = "%0" + 10 + "d";
		return String.format(f, Integer.valueOf(number));
	}
	

}
