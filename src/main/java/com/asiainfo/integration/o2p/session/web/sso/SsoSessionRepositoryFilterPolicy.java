package com.asiainfo.integration.o2p.session.web.sso;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asiainfo.integration.o2p.session.web.sso.SsoRedisOperationsSessionRepository.RedisSession;
import com.asiainfo.integration.o2p.web.util.WebConstants;


public class SsoSessionRepositoryFilterPolicy  {
	
    public static final String SESSION_REPOSITORY_ATTR = SsoSessionRepository.class.getName();

    public static final int DEFAULT_ORDER = Integer.MIN_VALUE + 50;

    private SsoSessionRepository ssoSessionRepository;
    
    private ServletContext servletContext;

    private SsoMultiHttpSessionStrategy httpSessionStrategy;


    public SsoSessionRepositoryFilterPolicy(SsoSessionRepository ssoSessionRepository,ServletContext servletContext) {
    	if(ssoSessionRepository == null) {
            throw new IllegalArgumentException("SsoSessionRepository cannot be null");
        }
    	this.ssoSessionRepository = ssoSessionRepository;
    	this.servletContext = servletContext;
    }
    

    public void setHttpSessionStrategy(SsoHttpSessionStrategy ssoHttpSessionStrategy) {
        if(ssoSessionRepository == null) {
            throw new IllegalArgumentException("httpSessionIdStrategy cannot be null");
        }
        this.httpSessionStrategy = new MultiHttpSessionStrategyAdapter(ssoHttpSessionStrategy);
    }


    public void setHttpSessionStrategy(SsoMultiHttpSessionStrategy httpSessionStrategy) {
        if(ssoSessionRepository == null) {
            throw new IllegalArgumentException("httpSessionIdStrategy cannot be null");
        }
        this.httpSessionStrategy = httpSessionStrategy;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute(SESSION_REPOSITORY_ATTR, ssoSessionRepository);

        SessionRepositoryRequestWrapper wrappedRequest = new SessionRepositoryRequestWrapper(request, response);
        SessionRepositoryResponseWrapper wrappedResponse = new SessionRepositoryResponseWrapper(wrappedRequest,response);

        HttpServletRequest strategyRequest = httpSessionStrategy.wrapRequest(wrappedRequest, wrappedResponse);
        HttpServletResponse strategyResponse = httpSessionStrategy.wrapResponse(wrappedRequest, wrappedResponse);
        
        filterChain.doFilter(strategyRequest, strategyResponse);
    }


    private final class SessionRepositoryResponseWrapper extends OnCommittedResponseWrapper {

        private final SessionRepositoryRequestWrapper request;


        public SessionRepositoryResponseWrapper(SessionRepositoryRequestWrapper request, HttpServletResponse response) {
            super(response);
            if(request == null) {
                throw new IllegalArgumentException("request cannot be null");
            }
            this.request = request;
        }

        @Override
        protected void onResponseCommitted() {
            request.commitSession();
        }
    }


    private final class SessionRepositoryRequestWrapper extends HttpServletRequestWrapper {
        private Boolean requestedSessionIdValid;
        private final HttpServletResponse response;
        private final HttpServletRequest request;
        private RedisSession currentSession;

        private SessionRepositoryRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
            super(request);
            this.request = request;
            this.response = response;
        }

        /**
         * 提交session
         */
        private void commitSession() {
        	if(this.currentSession == null){
        		if(isInvalidateClientSession()) {
                    httpSessionStrategy.onInvalidateSession(this, response);
                }
        	}else{
	        	HttpSession session = super.getSession();
	        	this.currentSession.setHttpSession(session);
	            ssoSessionRepository.save(currentSession);
	            boolean isRequestedSessionIdValid = isRequestedSessionIdValid();
	            if(!isRequestedSessionIdValid || !currentSession.getId().equals(getRequestedSessionId())) {
	            	httpSessionStrategy.onNewSession(currentSession, this, response); 
	            }    
        	}
        }

        public boolean isRequestedSessionIdValid() {
            if(requestedSessionIdValid == null) {
                String sessionId = getRequestedSessionId();
                RedisSession session = sessionId == null ? null : ssoSessionRepository.getSession(sessionId);
                return isRequestedSessionIdValid(session);
            }

            return requestedSessionIdValid;
        }

        private boolean isRequestedSessionIdValid(RedisSession session) {
            if(requestedSessionIdValid == null) {
                requestedSessionIdValid = session != null;
            }
            return requestedSessionIdValid;
        }
        
        private boolean isInvalidateClientSession() {
            return currentSession == null && isRequestedSessionIdValid();
        }
        
        @Override
        public HttpSession getSession(boolean create) {
        	HttpSession session = super.getSession(create); 
        	if(session != null){
	        	String requestedSessionId = getRequestedSessionId();
	        	if(requestedSessionId != null){
	        		//从缓存中获取Session
	                RedisSession redisSession = ssoSessionRepository.getSession(requestedSessionId);
	                if(redisSession != null) {
	                	this.currentSession = redisSession;
	                    this.requestedSessionIdValid = true;
	                    if(redisSession.getPortalUserInfo() != null){
	                    	session.setAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY, redisSession.getPortalUserInfo());
	                    }
	                    if(redisSession.getSsoUserInfo() != null){
	                    	session.setAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY, redisSession.getSsoUserInfo());
	                    }
	                    return session;
	                }
	        	}
	        	RedisSession redisSession = ssoSessionRepository.createSession(session);
	        	currentSession = redisSession;
        	}
        	return session;
        }


        @Override
        public HttpSession getSession() {
            return getSession(true);
        }
        

        @Override
        public String getRequestedSessionId() {
            return httpSessionStrategy.getRequestedSessionId(this);
        }
        
    	@Override
    	public String getParameter(String name) {
    		String value = super.getParameter(name);
    		if (value != null) {
    			//如果是get,转义
    			if(this.request.getMethod().equalsIgnoreCase("get")){
    				value = this.xssEncode(value);
    			}
    		}
    		return value;
    	}
    	
    	/**
    	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
    	 * 
    	 * @param s
    	 * @return
    	 */
    	private String xssEncode(String s) {
    		if (s == null || "".equals(s)) {
    			return s;
    		}
    		StringBuilder sb = new StringBuilder(s.length() + 16);
    		for (int i = 0; i < s.length(); i++) {
    			char c = s.charAt(i);
    			switch (c) {
    			case '>':
    				sb.append('＞');// 全角大于号
    				break;
    			case '<':
    				sb.append('＜');// 全角小于号
    				break;
    			case '\'':
    				sb.append('‘');// 全角单引号
    				break;
    			case '\"':
    				sb.append('“');// 全角双引号
    				break;
    			case '&':
    				sb.append('＆');// 全角
    				break;
    			case '\\':
    				sb.append('＼');// 全角斜线
    				break;
    			case '#':
    				sb.append('＃');// 全角井号
    				break;
    			default:
    				sb.append(c);
    				break;
    			}
    		}
    		return sb.toString();
    	}
    }
    
    


    static class MultiHttpSessionStrategyAdapter implements SsoMultiHttpSessionStrategy {
        private SsoHttpSessionStrategy delegate;

        public MultiHttpSessionStrategyAdapter(SsoHttpSessionStrategy delegate) {
            this.delegate = delegate;
        }

        public String getRequestedSessionId(HttpServletRequest request) {
            return delegate.getRequestedSessionId(request);
        }

        public void onNewSession(RedisSession redisSession,HttpServletRequest request,
                HttpServletResponse response) {
            delegate.onNewSession(redisSession,request, response);
        }

        public void onInvalidateSession(HttpServletRequest request,
                HttpServletResponse response) {
            delegate.onInvalidateSession(request, response);
        }

        public HttpServletRequest wrapRequest(HttpServletRequest request,
                HttpServletResponse response) {
            return request;
        }

        public HttpServletResponse wrapResponse(HttpServletRequest request,
                HttpServletResponse response) {
            return response;
        }
    }
}
