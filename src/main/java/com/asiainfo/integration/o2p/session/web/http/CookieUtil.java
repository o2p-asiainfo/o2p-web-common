package com.asiainfo.integration.o2p.session.web.http;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import com.ailk.eaap.o2p.common.util.CustomBase64;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.O2pWebCommonUtil;
import com.asiainfo.integration.o2p.web.util.WebConstants;
import com.linkage.rainbow.util.StringUtil;

/**
 * @ClassName: CookieUtil
 * @Description: 
 * @author zhengpeng
 * @date 2015-5-21 下午8:25:30
 *
 */
public class CookieUtil {
	
	private static final Logger log = Logger.getLog(CookieUtil.class);
    private static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
	private static final String COOKIE_DATE_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss 'GMT'";
	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(COOKIE_DATE_PATTERN, GMT_TIME_ZONE, Locale.US);
	
	public static O2pCookie getCookie(String path,String sessionId,HttpServletRequest request){
    	O2pCookie cookie = new O2pCookie(CookieHttpSessionStrategy.COOKIE_NAME,sessionId);
		cookie.setSecure(request.isSecure());
		cookie.setPath(path);
		cookie.setHttpOnly(true);
		cookie.setValue(sessionId);
        return cookie;
    }
	
	public static String buildHttpOnlyCookieString(O2pCookie cookie) {
		StringBuilder cookieBuilder = new StringBuilder();

		cookieBuilder.append(cookie.getName()).append("=").append(cookie.getValue());
		cookieBuilder.append(";");

		if (StringUtils.isNotBlank(cookie.getDomain())) {
			cookieBuilder.append("Domain").append("=").append(
					cookie.getDomain());
			cookieBuilder.append(";");
		}

		if (StringUtils.isNotBlank(cookie.getPath())) {
			cookieBuilder.append("Path").append("=").append(cookie.getPath());
			cookieBuilder.append(";");
		}

		if (cookie.getMaxAge() >= 0) {
			cookieBuilder.append("Expires").append("=").append(getCookieExpires(cookie.getMaxAge()));
			cookieBuilder.append(";");
		}
		if(cookie.getSecure()){
			cookieBuilder.append("Secure;");
		}
		cookieBuilder.append("HttpOnly");
		return cookieBuilder.toString();
	}
	
	public static String getCookieExpires(int maxAge) {
		String result = null;
		if (maxAge > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, maxAge);
			result = DATE_FORMAT.format(calendar);
			// maxAge == 0
		} else { 
			// maxAge为0时表示需要删除该cookie，因此将时间设为最小时间，即1970年1月1日
			result = DATE_FORMAT.format(0); 
		}
		return result;
	}
	
	 public static void addCookie(O2pCookie cookie,HttpServletResponse response) {
			if (!cookie.isHttpOnly()) {
				response.addCookie(cookie);
			} else {
				String cookieString = CookieUtil.buildHttpOnlyCookieString(cookie);
				response.setHeader("Set-Cookie", cookieString);
			}
		}
	 
	 public static UserRoleInfo getUserRoleInfo(HttpServletRequest request){
		UserRoleInfo userRoleInfo = new UserRoleInfo();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(EAAPConstants.O2P_USER_NAME.equals(cookie.getName())){
					if(!StringUtil.isEmpty(cookie.getValue())){
			    		String userInfo = cookie.getValue();
			    		if(!StringUtil.isEmpty(userInfo)){
							byte[] bytes =CustomBase64.decode(userInfo);
							userInfo = new String(bytes);
							String[] userInfos = userInfo.split(",");
							if(userInfos != null && userInfos.length == 2){
								userRoleInfo.setName(userInfos[0]);
								userRoleInfo.setId(userInfos[1]);  
							}
							break;
						}
					}
				}
			}
		}
		return userRoleInfo; 
	 }
	 
	 public static String getTenantId(HttpServletRequest request) {
		return String.valueOf(CookieUtil.getSSOTenantId(request)); 
	 } 
	 
	 public static Integer getSSOTenantId(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 UserRoleInfo userRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
		 Integer tenantId = O2pWebCommonUtil.getDefalutTenantId();
		 if(userRoleInfo != null && userRoleInfo.getTenantId() != null){
			 tenantId = userRoleInfo.getTenantId();
		 }
		 return tenantId;
	 }
	 
	 public static Integer getPortalTenantId(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 UserRoleInfo userRoleInfo = (UserRoleInfo) session.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY); 
		 Integer tenantId = O2pWebCommonUtil.getDefalutTenantId();
		 if(userRoleInfo != null && userRoleInfo.getTenantId() != null){
			 tenantId = userRoleInfo.getTenantId();
		 }
		 return tenantId;
	 }

}
