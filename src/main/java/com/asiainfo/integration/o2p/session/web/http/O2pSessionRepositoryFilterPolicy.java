package com.asiainfo.integration.o2p.session.web.http;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;


public class O2pSessionRepositoryFilterPolicy<S extends ExpiringSession>  {
	
    public static final String SESSION_REPOSITORY_ATTR = SessionRepository.class.getName();

    public static final int DEFAULT_ORDER = Integer.MIN_VALUE + 50;

    private SessionRepository<S> sessionRepository;
    
    private ServletContext servletContext;

    private MultiHttpSessionStrategy httpSessionStrategy;


    public O2pSessionRepositoryFilterPolicy(SessionRepository<S> sessionRepository,ServletContext servletContext) {
    	if(sessionRepository == null) {
            throw new IllegalArgumentException("SessionRepository cannot be null");
        }
    	this.sessionRepository = sessionRepository;
    	this.servletContext = servletContext;
    }
    

    public void setHttpSessionStrategy(HttpSessionStrategy httpSessionStrategy) {
        if(sessionRepository == null) {
            throw new IllegalArgumentException("httpSessionIdStrategy cannot be null");
        }
        this.httpSessionStrategy = new MultiHttpSessionStrategyAdapter(httpSessionStrategy);
    }


    public void setHttpSessionStrategy(MultiHttpSessionStrategy httpSessionStrategy) {
        if(sessionRepository == null) {
            throw new IllegalArgumentException("httpSessionIdStrategy cannot be null");
        }
        this.httpSessionStrategy = httpSessionStrategy;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute(SESSION_REPOSITORY_ATTR, sessionRepository);

        SessionRepositoryRequestWrapper wrappedRequest = new SessionRepositoryRequestWrapper(request, response);
        SessionRepositoryResponseWrapper wrappedResponse = new SessionRepositoryResponseWrapper(wrappedRequest,response);

        HttpServletRequest strategyRequest = httpSessionStrategy.wrapRequest(wrappedRequest, wrappedResponse);
        HttpServletResponse strategyResponse = httpSessionStrategy.wrapResponse(wrappedRequest, wrappedResponse);
        //strategyResponse.addHeader("x-frame-options","SAMEORIGIN"); 
        
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
        private HttpSessionWrapper currentSession;
        private Boolean requestedSessionIdValid;
        private final HttpServletResponse response;
        private final HttpServletRequest request;

        private SessionRepositoryRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
            super(request);
            this.request = request;
            this.response = response;
        }

        /**
         * 提交session
         */
        private void commitSession() {
            HttpSessionWrapper wrappedSession = currentSession;
            if(wrappedSession == null) {
                if(isInvalidateClientSession()) {
                    httpSessionStrategy.onInvalidateSession(this, response);
                }
            } else {
                S session = wrappedSession.session;
                sessionRepository.save(session);
                boolean isRequestedSessionIdValid = isRequestedSessionIdValid();
                if(!isRequestedSessionIdValid || !session.getId().equals(getRequestedSessionId())) {
                	httpSessionStrategy.onNewSession(session, this, response);
                }
            }
        }

        public boolean isRequestedSessionIdValid() {
            if(requestedSessionIdValid == null) {
                String sessionId = getRequestedSessionId();
                S session = sessionId == null ? null : sessionRepository.getSession(sessionId);
                return isRequestedSessionIdValid(session);
            }

            return requestedSessionIdValid;
        }

        private boolean isRequestedSessionIdValid(S session) {
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
            if(currentSession != null) {
                return currentSession;
            }
            String requestedSessionId = getRequestedSessionId();
            if(requestedSessionId != null) {
            	//从缓存中获取Session
                S session = sessionRepository.getSession(requestedSessionId);
                if(session != null) {
                    this.requestedSessionIdValid = true;
                    currentSession = new HttpSessionWrapper(session, servletContext);
                    currentSession.setNew(false);
                    return currentSession;
                }
            }
            if(!create) {
                return null;
            }
            S session = sessionRepository.createSession();
            currentSession = new HttpSessionWrapper(session, servletContext);
            return currentSession;
        }

//        @Override
//        public HttpSession getSession(boolean create) {
//        	if(currentSession != null) {
//              return currentSession;
//            }else{
//            	 String requestedSessionId = getRequestedSessionId();
//                 if(requestedSessionId != null) {
//                 	//从缓存中获取Session
//                     S session = sessionRepository.getSession(requestedSessionId);
//                     if(session != null) {
//                         this.requestedSessionIdValid = true;
//                         currentSession = new HttpSessionWrapper(session,servletContext);
//                         currentSession.setNew(false);
//                         return currentSession;
//                     }
//                 }
//                 if(create) {
//                	//新建Session
//      	            S session = sessionRepository.createSession();
//      	            currentSession = new HttpSessionWrapper(session,servletContext);
//      	            return currentSession;
//                 }else{
//                	 return null;
//                 }
//            }
//        }

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


        private final class HttpSessionWrapper implements HttpSession {
            private final S session;
            private final ServletContext servletContext;
            private boolean invalidated;
            private boolean old;

            public HttpSessionWrapper(S session,ServletContext servletContext) {
                this.session = session;
                this.servletContext = servletContext;
            }

            public long getCreationTime() {
                checkState();
                return session.getCreationTime();
            }

            public String getId() {
                return session.getId();
            }

            public long getLastAccessedTime() {
                checkState();
                return session.getLastAccessedTime();
            }

            public ServletContext getServletContext() {
            	return servletContext;
            }

            public void setMaxInactiveInterval(int interval) {
                session.setMaxInactiveIntervalInSeconds(interval);
            }

            public int getMaxInactiveInterval() {
                return session.getMaxInactiveIntervalInSeconds();
            }

            @SuppressWarnings("deprecation")
            public HttpSessionContext getSessionContext() {
                return NOOP_SESSION_CONTEXT;
            }

            public Object getAttribute(String name) {
                checkState();
                return session.getAttribute(name);
            }

            public Object getValue(String name) {
                return getAttribute(name);
            }

            public Enumeration<String> getAttributeNames() {
                checkState();
                return Collections.enumeration(session.getAttributeNames());
            }

            public String[] getValueNames() {
                checkState();
                Set<String> attrs = session.getAttributeNames();
                return attrs.toArray(new String[0]);
            }

            public void setAttribute(String name, Object value) {
                checkState();
                session.setAttribute(name, value);
            }

            public void putValue(String name, Object value) {
                setAttribute(name, value);
            }

            public void removeAttribute(String name) {
                checkState();
                session.removeAttribute(name);
            }

            public void removeValue(String name) {
                removeAttribute(name);
            }

            public void invalidate() {
                checkState();
                this.invalidated = true;
                currentSession = null;
                sessionRepository.delete(getId());
            }

            public void setNew(boolean isNew) {
                this.old = !isNew;
            }

            public boolean isNew() {
                checkState();
                return !old;
            }

            private void checkState() {
                if(invalidated) {
                    throw new IllegalStateException("The HttpSession has already be invalidated.");
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static final HttpSessionContext NOOP_SESSION_CONTEXT = new HttpSessionContext() {
        public HttpSession getSession(String sessionId) {
            return null;
        }

        public Enumeration<String> getIds() {
            return EMPTY_ENUMERATION;
        }
    };

    private static final Enumeration<String> EMPTY_ENUMERATION = new Enumeration<String>() {
        public boolean hasMoreElements() {
            return false;
        }

        public String nextElement() {
            throw new NoSuchElementException("a");
        }
    };

    static class MultiHttpSessionStrategyAdapter implements MultiHttpSessionStrategy {
        private HttpSessionStrategy delegate;

        public MultiHttpSessionStrategyAdapter(HttpSessionStrategy delegate) {
            this.delegate = delegate;
        }

        public String getRequestedSessionId(HttpServletRequest request) {
            return delegate.getRequestedSessionId(request);
        }

        public void onNewSession(Session session, HttpServletRequest request,
                HttpServletResponse response) {
            delegate.onNewSession(session, request, response);
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
