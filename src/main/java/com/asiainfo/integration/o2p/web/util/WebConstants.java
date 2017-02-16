package com.asiainfo.integration.o2p.web.util;

/**
 * @ClassName: WebConstants
 * @Description: 
 * @author zhengpeng
 * @date 2015-9-8 上午9:47:27
 *
 */
public class WebConstants {
	
	public static final String O2P_SSO_USER_SESSION_KEY = "O2P_SSO_USER_SESSION_KEY";
	public static final String O2P_PORTAL_USER_SESSION_KEY = "O2P_PORTAL_USER_SESSION_KEY"; 
	public static final String O2P_USER_TENANT_ID_KEY = "USER_TENANT_ID"; 
	
	public static final int SUCCESS_RESULT = 1;
	public static final int FAIL_RESULT = 0;
	
	public static final String RESULT_OK = "0000";
	public static final String RESULT_ERR = "0001";
	public static final String RETURN_CODE = "code";
	public static final String RETURN_DESC = "desc";
	
	/** webservice 发送次数 **/
	public static final Integer WEBSERVICE_SEND_TIMES = 3;
	
	/** webservice 发送超时时间 **/
	public static final Integer WEBSERVICE_SEND_TIME_OUT = 30;
	
	
	public static final String OTT_CRM_ORDER = "ott_crm_order";
	public static final String CLOUD_SYNC_PRODUCT = "cloud_sync_product";
	public static final String CLOUD_SYNC_PRODOFFER = "cloud_sync_prodoffer";
	
	public static final String PRODUCT_TYPE = "P"; 
	public static final String PRODOFFER_TYPE = "O";  
	public static final String RTI_TYPE = "R";  
	
	
	//等待处理
	public static final String OTT_TASK_SCHEDULE_W = "W";
	//正在处理
	public static final String OTT_TASK_SCHEDULE_R = "R";
	//处理结束
	public static final String OTT_TASK_SCHEDULE_F = "F";
	//处理异常
	public static final String OTT_TASK_SCHEDULE_E = "E";
	
	//节点类型 end
	public static final String OTT_TACHE_E = "E";
	//节点类型 service
	public static final String OTT_TACHE_S = "S";
	//节点类型 receive
	public static final String OTT_TACHE_R = "R";
	
	//OTT异常处理方式  自动执行(A)
	public static final String OTT_DEAL_MODEL_A = "A";
	//OTT异常处理方式  跳过(J)
	public static final String OTT_DEAL_MODEL_J = "J";
}
