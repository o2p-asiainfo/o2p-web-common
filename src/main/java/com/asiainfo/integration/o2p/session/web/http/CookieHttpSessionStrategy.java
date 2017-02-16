package com.asiainfo.integration.o2p.session.web.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;

import com.ailk.eaap.o2p.common.spring.config.CustomPropertyConfigurer;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;


public final class CookieHttpSessionStrategy implements MultiHttpSessionStrategy, HttpSessionManager {

	static final String DEFAULT_ALIAS = "0";

    static final String DEFAULT_SESSION_ALIAS_PARAM_NAME = "_s";

    private Pattern ALIAS_PATTERN = Pattern.compile("^[\\w-]{1,50}$");

    public static final  String COOKIE_NAME = "SESSIONID"; 

    private String sessionParam = DEFAULT_SESSION_ALIAS_PARAM_NAME;
    
    private static final String SSO_PATH = "/o2p-sso";
    private static final String PORTAL_PATH = "/o2p-portal";
   
    
    /**
     * 获取SessionId
     */
    public String getRequestedSessionId(HttpServletRequest request) {
        Map<String,String> sessionIds = getSessionIds(request);
        String sessionAlias = getCurrentSessionAlias(request);
        return sessionIds.get(sessionAlias);
    }
    
    public String getCurrentSessionAlias(HttpServletRequest request) {
        if(sessionParam == null) {
            return DEFAULT_ALIAS;
        }
        String u = request.getParameter(sessionParam);
        if(u == null) {
            return DEFAULT_ALIAS;
        }
        if(!ALIAS_PATTERN.matcher(u).matches()) {
            return DEFAULT_ALIAS;
        }
        return u;
    }

    public String getNewSessionAlias(HttpServletRequest request) {
        Set<String> sessionAliases = getSessionIds(request).keySet();
        if(sessionAliases.isEmpty()) {
            return DEFAULT_ALIAS;
        }
        long lastAlias = Long.decode(DEFAULT_ALIAS);
        for(String alias : sessionAliases) {
            long selectedAlias = safeParse(alias);
            if(selectedAlias > lastAlias) {
                lastAlias = selectedAlias;
            }
        }
        return Long.toHexString(lastAlias + 1);
    }

    private long safeParse(String hex) {
        try {
            return Long.decode("0x" + hex);
        } catch(NumberFormatException notNumber) {
            return 0;
        }
    }
    
    /**
     * 创建Session和SessionId
     */
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> sessionIds = getSessionIds(request);
        String sessionAlias = getCurrentSessionAlias(request);
        sessionIds.put(sessionAlias, session.getId());
        List<O2pCookie> cookieList = createSessionCookies(request, sessionIds);
        for(O2pCookie cookie : cookieList){
        	this.addCookie(cookie, response);
        }
    }
    
    

    private List<O2pCookie> createSessionCookies(HttpServletRequest request,
            Map<String, String> sessionIds) {
    	List<O2pCookie> cookieList = new ArrayList<O2pCookie>();
    	String isCrossDomainStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_CROSS_DOMAIN);
    	if(!StringUtils.isEmpty(isCrossDomainStr)){
    		//是否跨域
    		boolean isCrossDomain = Boolean.valueOf(isCrossDomainStr);
    		//否
    		if(!isCrossDomain){
    			String webConfPathsStr = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_CONF_PATHS);
    			cookieList.addAll(this.getCookieListByPath(webConfPathsStr, request, sessionIds));
    			
    	    	String webPortalPathsString = WebPropertyUtil.getPropertyValue(EAAPConstants.WEB_PORTAL_PATHS);
    	    	cookieList.addAll(this.getCookieListByPath(webPortalPathsString, request, sessionIds));
    
    		}else{
//    			String requestPath = request.getContextPath();
//    			if(requestPath.equals(SSO_PATH)){
//    				String webPortalPathsString = CustomPropertyConfigurer.getProperty(EAAPConstants.WEB_CONF_PATHS);
//        	    	cookieList.addAll(this.getCookieListByPath(webPortalPathsString, request, sessionIds));
//    			}else if(requestPath.equals(PORTAL_PATH)){
//    				String webPortalPathsString = CustomPropertyConfigurer.getProperty(EAAPConstants.WEB_PORTAL_PATHS);
//        	    	cookieList.addAll(this.getCookieListByPath(webPortalPathsString, request, sessionIds));
//    			}else{
//	    			O2pCookie cookie = this.getCookie(request.getContextPath(), sessionIds, request);
//	        		cookieList.add(cookie);
//    			}
    		}
    	}else{
    		O2pCookie cookie = this.getCookie(request.getContextPath(), sessionIds, request);
    		cookieList.add(cookie);
    	}
        return cookieList;
    }
    
    private List<O2pCookie> getCookieListByPath(String webPath,HttpServletRequest request,
            Map<String, String> sessionIds){
    	List<O2pCookie> cookieList = new ArrayList<O2pCookie>();
    	if(!StringUtils.isEmpty(webPath)){
    		String[] webPaths =  webPath.split(",");
    		for(String path : webPaths){
    			O2pCookie cookie = this.getCookie(path, sessionIds, request);
    	        cookieList.add(cookie);
    		}
    	}
    	return cookieList;
    }
    
    private O2pCookie getCookie(String path,Map<String, String> sessionIds,HttpServletRequest request){
    	O2pCookie cookie = new O2pCookie(COOKIE_NAME,"");
		cookie.setSecure(request.isSecure());
		cookie.setPath(path);
		cookie.setHttpOnly(true);

        if(sessionIds.isEmpty()){
        	cookie.setMaxAge(60*60*24); 
        }else if(sessionIds.size() == 1){
        	String cookieValue = sessionIds.values().iterator().next();
        	cookie.setValue(cookieValue);
        }else{
        	StringBuffer buffer = new StringBuffer();
	        for(Map.Entry<String,String> entry : sessionIds.entrySet()) {
	            String alias = entry.getKey();
	            String id = entry.getValue();
	
	            buffer.append(alias);
	            buffer.append(" ");
	            buffer.append(id);
	            buffer.append(" ");
	        }
	        buffer.deleteCharAt(buffer.length()-1);
	        cookie.setValue(buffer.toString());
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
        Map<String,String> sessionIds = getSessionIds(request);
//        String requestedAlias = getCurrentSessionAlias(request);
//        sessionIds.remove(requestedAlias);
        
        List<O2pCookie> sessionCookieList = createSessionCookies(request, sessionIds);
        for(O2pCookie cookie : sessionCookieList){
        	this.addCookie(cookie, response);
        } 
    }


    public void setSessionAliasParamName(String sessionAliasParamName) {
        this.sessionParam = sessionAliasParamName;
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

    private static String cookiePath(HttpServletRequest request) {
        return request.getContextPath() + "/";
    }

    public Map<String,String> getSessionIds(HttpServletRequest request) {
    	//获取sessionId的Cookie
        Cookie session = getCookie(request, COOKIE_NAME);
        String sessionCookieValue = session == null ? "" : session.getValue();
        Map<String,String> result = new LinkedHashMap<String,String>();
        StringTokenizer tokens = new StringTokenizer(sessionCookieValue, " ");
        if(tokens.countTokens() == 1) {
            result.put(DEFAULT_ALIAS, tokens.nextToken());
            return result;
        }
        while(tokens.hasMoreTokens()) {
            String alias = tokens.nextToken();
            if(!tokens.hasMoreTokens()) {
                break;
            }
            String id = tokens.nextToken();
            result.put(alias, id);
        }
        return result;
    }

    public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(HttpSessionManager.class.getName(), this);
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
            url = super.encodeRedirectURL(url);
            return CookieHttpSessionStrategy.this.encodeURL(url, getCurrentSessionAlias(request));
        }

        @Override
        public String encodeURL(String url) {
            url = super.encodeURL(url);

            String alias = getCurrentSessionAlias(request);
            return CookieHttpSessionStrategy.this.encodeURL(url, alias);
        }
    }

    public String encodeURL(String url, String sessionAlias) {
        String encodedSessionAlias = urlEncode(sessionAlias);
        int queryStart = url.indexOf("?");
        boolean isDefaultAlias = DEFAULT_ALIAS.equals(encodedSessionAlias);
        if(queryStart < 0) {
            return isDefaultAlias ? url : url + "?" + sessionParam + "=" + encodedSessionAlias;
        }
        String path = url.substring(0, queryStart);
        String query = url.substring(queryStart + 1, url.length());
        String replacement = isDefaultAlias ? "" : "$1"+encodedSessionAlias;
        query = query.replaceFirst( "((^|&)" + sessionParam + "=)([^&]+)?", replacement);
        if(!isDefaultAlias && url.endsWith(query)) {
            // no existing alias
            if(!(query.endsWith("&") || query.length() == 0)) {
                query += "&";
            }
            query += sessionParam + "=" + encodedSessionAlias;
        }

        return path + "?" + query;
    }

    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}