/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:PartyRoleInfo.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月9日上午10:09:38 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:PartyRoleInfo <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月9日 上午10:09:38 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyRoleInfo", propOrder = {
    "partyRoleID",
    "partyRoleSpecID",
    "action",
    "partyRoleAddress",
    "opId",
    "orgId"
})
public class PartyRoleInfo {
	
	@XmlElement(name = "PartyRoleID")
    protected BigInteger partyRoleID;
    @XmlElement(name = "PartyRoleSpecID")
    protected Integer partyRoleSpecID;
    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "PartyRoleAddress")
    protected String partyRoleAddress;
    @XmlElement(name = "OpId", required = true)
    protected String opId;
    @XmlElement(name = "OrgId", required = true)
    protected String orgId;
	public BigInteger getPartyRoleID() {
		return partyRoleID;
	}
	public void setPartyRoleID(BigInteger partyRoleID) {
		this.partyRoleID = partyRoleID;
	}
	public Integer getPartyRoleSpecID() {
		return partyRoleSpecID;
	}
	public void setPartyRoleSpecID(Integer partyRoleSpecID) {
		this.partyRoleSpecID = partyRoleSpecID;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getPartyRoleAddress() {
		return partyRoleAddress;
	}
	public void setPartyRoleAddress(String partyRoleAddress) {
		this.partyRoleAddress = partyRoleAddress;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
    
    
}
