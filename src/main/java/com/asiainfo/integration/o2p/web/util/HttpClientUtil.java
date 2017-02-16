package com.asiainfo.integration.o2p.web.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.ailk.eaap.op2.common.StringUtil;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

public class HttpClientUtil {

	private static final Logger log = Logger.getLog(HttpClientUtil.class);
	 
	public final static String POST_METHOD = "POST";
	public final static String GET_METHOD = "GET";
	public final static String CONTENT_TYPE_XML_UTF8 = "text/xml;charset=UTF-8";
	public final static String CONTENT_TYPE_PLAIN_UTF8 = "text/plain;charset=UTF-8";
	public final static String CONTENT_TYPE_JSON_UTF8 = "application/json;charset=UTF-8";

	public static String sendRequest(String address,  String msg, String method,  int timeout,String serviceName,String userName,String password,String proxyIp,String proxyPort){
		return sendRequest(address, null, msg, CONTENT_TYPE_XML_UTF8, method, timeout,serviceName,userName,password,proxyIp,proxyPort);
	}
	
	/**
	 * @param address
	 * @param reqHead
	 * @param msg
	 * @param contentType
	 * @param method
	 * @param timeout 超时时间
	 * @return
	HttpClientUtil.java * @throws CSBException
	 */
	public static String sendRequest(String address, Map<String, String> reqHead,  String msg, String contentType, String method,  int timeout,
			String servicename,String userName,String password,String proxyIP,String proxyPort) {		
		
		HttpMethod httpMethod = null;
		HttpClient httpClient = new HttpClient();
		String returnStr = "";
		try{
			if(POST_METHOD.equalsIgnoreCase(method)){				
				httpMethod = new PostMethod(address);
				((PostMethod)httpMethod).setRequestEntity(new StringRequestEntity(msg != null ? msg : "", contentType, "UTF-8"));
			}
			else{
				log.debug("get");
				httpMethod = new GetMethod(address);
			}
			if(userName !=null && !userName.equals("")){

				httpClient.getParams().setAuthenticationPreemptive(true); 
				
				Credentials  usernamePassword = new UsernamePasswordCredentials(
	                    userName, password);
				URL url = new URL(address);
				
				httpClient.getState().setCredentials(
			            new AuthScope(url.getHost(), 443, "realm"),
			            usernamePassword
			        );
				httpMethod.setDoAuthentication( true );
				
			}
			
			if(proxyIP!=null && !proxyIP.equals("")){
				log.debug("proxyIP");
				httpClient.getHostConfiguration().setProxy(proxyIP, Integer.parseInt(proxyPort));  
				httpClient.getParams().setAuthenticationPreemptive(true);  

			}
			
			if(reqHead != null){
				Iterator it=reqHead.entrySet().iterator();
				Map.Entry entry =null;
				while(it.hasNext()){
					entry = (Map.Entry)it.next();
					httpMethod.addRequestHeader(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));			
				}
			}
			
			httpClient.getParams().setConnectionManagerTimeout(timeout * 1000);
			httpClient.getParams().setSoTimeout(timeout * 1000);
			httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(3, false));
			
			int statusCode = httpClient.executeMethod(httpMethod);

			if(statusCode != HttpStatus.SC_OK){
				String erro = httpMethod.getResponseBodyAsString();
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,erro,null));
				throw new BusinessException(ExceptionCommon.WebExceptionCode,erro, null);
			}
			returnStr = StringUtil.getString(httpMethod.getResponseBodyAsStream(),"UTF-8");
			
		}
		catch (Exception e) {
			throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), null);
		}finally{
			try{
				if(httpMethod != null){
					httpMethod.releaseConnection();
				}
				if (httpClient != null) {
					((SimpleHttpConnectionManager) (httpClient.getHttpConnectionManager())).shutdown();
				}
			}catch (Exception e) {
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),e));
				throw new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(), e);
			}
		}
		return returnStr;
	}
	
	
	public static String sendJsonRequest(String address,  String msg, String method,  int timeout,String serviceName,String userName,String password,String proxyIp,String proxyPort,String localName){
		
		Map<String, String> reqHead=new HashMap<String, String>();
		reqHead.put("LocalName", localName);
		return sendRequest(address, reqHead, msg, CONTENT_TYPE_JSON_UTF8, method, timeout,serviceName,userName,password,proxyIp,proxyPort);
	}
}
