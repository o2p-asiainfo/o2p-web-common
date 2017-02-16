package com.asiainfo.integration.o2p.session.web.sso;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integration.o2p.web.util.WebConstants;

/**
 * @ClassName: UserSession
 * @Description:
 * @author zhengpeng
 * @date 2016-1-6 上午10:24:39
 * 
 */
public final class UserSession implements Serializable {

	public static final int DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800;

	private String id = StringUtils.remove(UUID.randomUUID().toString(), "-");
	private long creationTime = System.currentTimeMillis();
	private long lastAccessedTime = creationTime;
	private int maxInactiveInterval = DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
	private UserRoleInfo ssoUserInfo;
	private UserRoleInfo portalUserInfo;

	public UserSession() {
	}

	public UserSession(HttpSession session) {
		if (session != null) {
			this.lastAccessedTime = session.getLastAccessedTime();
			this.ssoUserInfo = (UserRoleInfo) session
					.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
			this.portalUserInfo = (UserRoleInfo) session
					.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY);
		}
	}
	
	public void setHttpSession(HttpSession session){
		if (session != null) {
			this.lastAccessedTime = session.getLastAccessedTime();
			this.ssoUserInfo = (UserRoleInfo) session
					.getAttribute(WebConstants.O2P_SSO_USER_SESSION_KEY);
			this.portalUserInfo = (UserRoleInfo) session
					.getAttribute(WebConstants.O2P_PORTAL_USER_SESSION_KEY);
		}
	}

	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public String getId() {
		return id;
	}

	public long getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setMaxInactiveIntervalInSeconds(int interval) {
		this.maxInactiveInterval = interval;
	}

	public int getMaxInactiveIntervalInSeconds() {
		return maxInactiveInterval;
	}

	public boolean isExpired() {
		return isExpired(System.currentTimeMillis());
	}

	boolean isExpired(long now) {
		if (maxInactiveInterval < 0) {
			return false;
		}
		return now - TimeUnit.SECONDS.toMillis(maxInactiveInterval) >= lastAccessedTime;
	}

	public UserRoleInfo getSsoUserInfo() {
		return ssoUserInfo;
	}

	public void setSsoUserInfo(UserRoleInfo ssoUserInfo) {
		this.ssoUserInfo = ssoUserInfo;
	}

	public UserRoleInfo getPortalUserInfo() {
		return portalUserInfo;
	}

	public void setPortalUserInfo(UserRoleInfo portalUserInfo) {
		this.portalUserInfo = portalUserInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSession other = (UserSession) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	private static final long serialVersionUID = 7160779239673823561L;

}
