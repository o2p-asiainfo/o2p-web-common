/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:PartyProfile.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月8日下午5:13:16 
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
 * ClassName:PartyProfile <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月8日 下午5:13:16 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyProfile", propOrder = {
    "partyID",
    "partyName",
    "shortName",
    "nationality",
    "action",
    "opId",
    "orgId",
    "identification",
    "organization",
    "partyRoleInfo",
    "contactInformation"
})
public class PartyProfile {

    @XmlElement(name = "PartyID")
    protected BigInteger partyID;
    @XmlElement(name = "PartyName", required = true)
    protected String partyName;
    @XmlElement(name = "ShortName")
    protected String shortName;
    @XmlElement(name = "Nationality")
    protected String nationality;
    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "OpId", required = true)
    protected String opId;
    @XmlElement(name = "OrgId", required = true)
    protected String orgId;
    @XmlElement(name = "Identification", required = true)
    protected Identification identification;
    @XmlElement(name = "Organization", required = true)
    protected Organization organization;
    @XmlElement(name = "PartyRoleInfo", required = true)
    protected PartyRoleInfo partyRoleInfo;
    @XmlElement(name = "ContactInformation", required = true)
    protected ContactInformation contactInformation;
    
    
	public BigInteger getPartyID() {
		return partyID;
	}
	public void setPartyID(BigInteger partyID) {
		this.partyID = partyID;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public Identification getIdentification() {
		return identification;
	}
	public void setIdentification(Identification identification) {
		this.identification = identification;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public PartyRoleInfo getPartyRoleInfo() {
		return partyRoleInfo;
	}
	public void setPartyRoleInfo(PartyRoleInfo partyRoleInfo) {
		this.partyRoleInfo = partyRoleInfo;
	}
	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
    
    
}
