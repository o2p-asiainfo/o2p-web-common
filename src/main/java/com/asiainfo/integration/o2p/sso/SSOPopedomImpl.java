package com.asiainfo.integration.o2p.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.ailk.eaap.o2p.common.util.CustomBase64;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.session.web.manage.SSOUserFactory;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.asiainfo.portal.framework.external.DefaultPopedomImpl;
import com.asiainfo.portal.framework.external.IPopedom;
import com.asiainfo.portal.framework.external.OperInfo;
import com.asiainfo.portal.framework.external.PortalDataFetch;


/**
 * CRM侧SSO客户端实现类
 * 
 *
 */
public class SSOPopedomImpl extends DefaultPopedomImpl implements IPopedom{
	
//	private final static String PORTAL_POPEDOMIMPL_SESSION =
//            "PORTAL_POPEDOMIMPL_SESSION"; //根据需要可改变为接入系统本身的SESSION
	private Logger log = Logger.getLog(this.getClass());

	@Override
	public boolean logout(String arg0, HttpSession session) throws Exception {
	    boolean flag = false;
	    if (session == null) {
	      flag = true;
	    }else{
		    String serialId = (String)session.getAttribute("USERINFO_ATTR");
		    log.debug("################### SSOPopedomImpl logout serialId:{0}",serialId); 
		    if (null == serialId || "".equals(serialId)) {
		      flag = true;
		    } else {
		      session.removeAttribute("USERINFO_ATTR");
		      flag = true;
		    }
		    session.invalidate();
	    }
	    log.debug("################### SSOPopedomImpl logout flag:{0}",flag); 
	    return flag;
	}

	@Override
	protected boolean doSelfSession(HttpServletRequest request,
			HttpServletResponse response, OperInfo operInfo) {
		boolean isSuccess = false;
		String code = operInfo.getOplogname();
		String operId = operInfo.getOpId();
		String tenantId = operInfo.getTenantId();
		String path = request.getServletPath();
		log.info("################### SSOPopedomImpl doSelfSession code:{0} ||operId:{1} ||tenantId:{2}",code,operId,tenantId); 
		// cookie中获取sessionID,塞进Session,作为用户SSO模拟登录成功的标识
		String sessionId = PortalDataFetch.getSessionId(request);
		log.info("################### SSOPopedomImpl doSelfSession sessionId:{0}",sessionId); 
		if(null != sessionId ){
			this.addUserNameToCookie(code, response);
			UserRoleInfo userRoleInfo = new UserRoleInfo();
			userRoleInfo.setId(operId);
			userRoleInfo.setName(code);
			userRoleInfo.setTenantId(Integer.valueOf(tenantId));  
			request.getSession().setAttribute(WebConstants.O2P_USER_TENANT_ID_KEY, tenantId); 
			request.getSession().setAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY, userRoleInfo);
			SSOUserFactory.getDefaultSsoUserCache().addUser(code, request.getSession().getId());
			isSuccess = true;
		}
		log.info("############ isSuccess:{0}",isSuccess);
	    return isSuccess;
	}

	/**
	 * 把用户名放入cookie
	 */
	private void addUserNameToCookie(String userName,HttpServletResponse response){
		String userNameBase64 = CustomBase64.encode(userName.getBytes());
		String cookieValue = EAAPConstants.O2P_USER_NAME + "=" + userNameBase64 + ";Path=/;HttpOnly;";
		response.setHeader("SET-COOKIE", cookieValue);
	}
	
	
	@Override
	protected boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
		//is_o2p_sso属性不为空,并且为true时 进行验证
		if(!StringUtils.isEmpty(WebPropertyUtil.getPropertyValue("is_o2p_sso")) && !Boolean.valueOf(WebPropertyUtil.getPropertyValue("is_o2p_sso"))){
			HttpSession session = request.getSession(); 
			log.info("###########################session:{0}",session);
			boolean isLogin = false;
			if(session != null){
				if(session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY) != null){
					isLogin = true;
				}else if(session.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY) != null){
					isLogin = true;
				}
			}
			log.info("###########################isLogin:{0}",isLogin); 
			return isLogin;
		}else{
			return true;
		}
	}
	
	
	
//	private String getAddress(String url){
//		Properties prop = null;
//		InputStream fis = null;
//		String addressResult = "";
//		if(null != url && !"".equals(url)){
//			fis = this.getClass().getResourceAsStream(url);
//			prop = new Properties();
//			try {
//				prop.load(fis);
//				if(null != prop.getProperty("sso.address")){
//					addressResult = prop.getProperty("sso.address");
//				}
//			} catch (FileNotFoundException e) {
//				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
//			} catch (IOException e){
//				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
//			}finally{
//				if(null != fis){
//					try {
//						fis.close();
//					} catch (IOException e) {
//						log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
//					}
//				}
//			}
//		}
//		return addressResult;
//	}
	
	
//	@Override
//	protected boolean doSelfSession(HttpServletRequest request,
//			HttpServletResponse response, OperInfo operInfo) {
//		boolean isSuccess = false;
//		 String code = operInfo.getOplogname();
//		 String operId = operInfo.getOpId();
//		 String path = request.getServletPath();
//		 boolean flagAll = false;
//		 boolean flagOwner = false;
//		 String common = request.getSession().getServletContext().getInitParameter("o2pConfigLocation");
//		 String adress = getAddress(common);
//		 String inMsg = "<ContractRoot><RequestHead> <AppKey>1</AppKey> <TransactionID>509751</TransactionID> <ReqTime>20131129103231</ReqTime>"
//					+"<Sign>***</Sign><AccessToken>a</AccessToken><Version>String</Version><TenantId>C</TenantId><AcceptChannelType>23</AcceptChannelType>"
//					+"<AcceptChannelCode>1000</AcceptChannelCode><AcceptStaffId>21OSE103</AcceptStaffId><BusiCode>GetLoginMenus</BusiCode> </RequestHead>"
//					+"<RequestBody>"
////					+ "<OperatorId>100000138</OperatorId>"
//					+ "<OperatorId>"+operId+"</OperatorId>"
//					+ "<MenuType>H</MenuType><EntClassId>9600</EntClassId></RequestBody></ContractRoot>";
//		 CRMWebServiceImplServiceClient client = new CRMWebServiceImplServiceClient(adress);
//		 SmDataSource smData = new SmDataSource();
//		 List<String> ownerMenu = client.getOperatorMenu(inMsg);
//		 List<String> allMenu = smData.getAllMenu();
//		 for(String all : allMenu){
//			 if(all.contains(path)){
//				 flagAll = true;
//				 break;
//			 }
//		 }
//		 for(String all : ownerMenu){
//			 if(all.contains(path)){
//				 flagOwner = true;
//				 break;
//			 }
//		 }
//			// cookie中获取sessionID,塞进Session,作为用户SSO模拟登录成功的标识
//			String sessionId = PortalDataFetch.getSessionId(request);
//			if(null != sessionId ){
//				this.addUserNameToCookie(code, response);
//				isSuccess = true;
//			}
//	      return isSuccess;
//	}
}
