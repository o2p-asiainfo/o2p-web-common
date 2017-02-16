package com.asiainfo.integration.o2p.session.web.http;

import javax.servlet.http.Cookie;
/**
 * @ClassName: O2pCookie
 * @Description:
 * @author zhengpeng
 * @date 2015-5-5 下午8:06:11
 * 
 */
public class O2pCookie extends Cookie {
	
	private boolean httpOnly = true;

	public O2pCookie(String name, String value) {
		super(name, value);
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	public boolean isHttpOnly() {
		return httpOnly;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDomain() == null) ? 0 : getDomain().hashCode());
		result = prime * result + (httpOnly ? 1231 : 1237);
		result = prime * result + getMaxAge();
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result
				+ ((getPath() == null) ? 0 : getPath().hashCode());
		result = prime * result
				+ ((getValue() == null) ? 0 : getValue().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		O2pCookie other = (O2pCookie) obj;
		if (getDomain() == null) {
			if (other.getDomain() != null)
				return false;
		} else if (!getDomain().equals(other.getDomain()))
			return false;
		if (httpOnly != other.httpOnly)
			return false;
		if (getMaxAge() != other.getMaxAge())
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (getPath() == null) {
			if (other.getPath() != null)
				return false;
		} else if (!getPath().equals(other.getPath()))
			return false;
		if (getValue() == null) {
			if (other.getValue() != null)
				return false;
		} else if (!getValue().equals(other.getValue()))
			return false;
		return true;
	}
}
