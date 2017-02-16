package com.asiainfo.integration.o2p.session.web.sso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;

import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.integration.o2p.session.web.http.CookieUtil;
import com.asiainfo.integration.o2p.session.web.http.O2pCookie;
import com.asiainfo.integration.o2p.session.web.sso.SsoRedisOperationsSessionRepository.RedisSession;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;


public final class SsoCookieHttpSessionStrategy implements SsoMultiHttpSessionStrategy {


    public static final  String COOKIE_NAME = "SESSIONID"; 

    /**
     * 获取SessionId
     */
    public String getRequestedSessionId(HttpServletRequest request) {
        return getSessionId(request);
    }
    

    public void onNewSession(RedisSession redisSession,HttpServletRequest request, HttpServletResponse response) {
        String sessionId = redisSession.getId(); 
        List<O2pCookie> cookieList = createSessionCookies(request, sessionId);
        for(O2pCookie cookie : cookieList){
        	this.addCookie(cookie, response);
        }
    }


    private List<O2pCookie> createSessionCookies(HttpServletRequest request,
            String sessionId) {
    	List<O2pCookie> cookieList = new ArrayList<O2pCookie>();
    	String isCrossDomainStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_CROSS_DOMAIN);
    	if(!StringUtils.isEmpty(isCrossDomainStr)){
    		//是否跨域
    		boolean isCrossDomain = Boolean.valueOf(isCrossDomainStr);
    		//否
    		if(!isCrossDomain){
    			String webConfPathsStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_CONF_PATHS);
    			cookieList.addAll(this.getCookieListByPath(webConfPathsStr, request, sessionId));
    			
    	    	String webPortalPathsString = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_PORTAL_PATHS);
    	    	cookieList.addAll(this.getCookieListByPath(webPortalPathsString, request, sessionId));
    	    //是
    		}else{
    		}
    	}else{
    		O2pCookie cookie = this.getCookie(request.getContextPath(), sessionId, request);
    		cookieList.add(cookie);
    	}
        return cookieList;
    }
    
    private List<O2pCookie> getCookieListByPath(String webPath,HttpServletRequest request,
    		String sessionId){
    	List<O2pCookie> cookieList = new ArrayList<O2pCookie>();
    	if(!StringUtils.isEmpty(webPath)){
    		String[] webPaths =  webPath.split(",");
    		for(String path : webPaths){
    			O2pCookie cookie = this.getCookie(path, sessionId, request);
    	        cookieList.add(cookie);
    		}
    	}
    	return cookieList;
    }
    
    private O2pCookie getCookie(String path,String sessionId,HttpServletRequest request){
    	O2pCookie cookie = new O2pCookie(COOKIE_NAME,"");
		cookie.setSecure(request.isSecure());
		cookie.setPath(path);
		cookie.setHttpOnly(true);

        if(sessionId.isEmpty()){
        	cookie.setMaxAge(60*60*24); 
        }else{
        	cookie.setValue(sessionId);
        }
        return cookie;
    }
    
    public void addCookie(O2pCookie cookie,HttpServletResponse response) {
		if (!cookie.isHttpOnly()) {
			response.addCookie(cookie);
		} else {
			String cookieString = CookieUtil.buildHttpOnlyCookieString(cookie);
			response.addHeader("Set-Cookie", cookieString);
		}
	}


    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = getSessionId(request);
        
        List<O2pCookie> sessionCookieList = createSessionCookies(request, sessionId);
        for(O2pCookie cookie : sessionCookieList){
        	this.addCookie(cookie, response);
        } 
    }


    
    private static Cookie getCookie(HttpServletRequest request, String name) {
        if(request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }


    public String getSessionId(HttpServletRequest request) {
    	//获取sessionId的Cookie
        Cookie sessionCookie = getCookie(request, COOKIE_NAME);
        String sessionCookieValue = sessionCookie == null ? "" : sessionCookie.getValue();
        return sessionCookieValue;
    }

    public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }

    public HttpServletResponse wrapResponse(HttpServletRequest request, HttpServletResponse response) {
        return new MultiSessionHttpServletResponse(response, request);
    }

    class MultiSessionHttpServletResponse extends HttpServletResponseWrapper {
        private final HttpServletRequest request;

        public MultiSessionHttpServletResponse(HttpServletResponse response, HttpServletRequest request) {
            super(response);
            this.request = request;
        }

        @Override
        public String encodeRedirectURL(String url) {
        	return super.encodeRedirectURL(url);
        }

        @Override
        public String encodeURL(String url) {
            return super.encodeURL(url);
        }
    }
    
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}