package com.asiainfo.integration.o2p.web.bo;

import java.io.Serializable;

/**
 * @ClassName: UserRoleInfo
 * @Description: 
 * @author zhengpeng
 * @date 2015-9-8 上午7:44:28
 *
 */
public class UserRoleInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Integer tenantId;
	private String tenantCode;
	
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
