/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:Organization.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月9日上午10:09:28 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:Organization <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月9日 上午10:09:28 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization", propOrder = {
    "upOrganizationID",
    "isLegalEntity",
    "tradingName",
    "memberNumber",
    "principalName",
    "registeredCapital",
    "legalName",
    "foundDate",
    "terminalDate"
})
public class Organization {
	    @XmlElement(name = "UpOrganizationID")
	    protected BigInteger upOrganizationID;
	    @XmlElement(name = "IsLegalEntity")
	    protected Integer isLegalEntity;
	    @XmlElement(name = "TradingName", required = true)
	    protected String tradingName;
	    @XmlElement(name = "MemberNumber")
	    protected BigInteger memberNumber;
	    @XmlElement(name = "PrincipalName")
	    protected String principalName;
	    @XmlElement(name = "RegisteredCapital")
	    protected String registeredCapital;
	    @XmlElement(name = "LegalName", required = true)
	    protected String legalName;
	    @XmlElement(name = "FoundDate")
	    protected Date foundDate;
	    @XmlElement(name = "TerminalDate")
	    protected Date terminalDate;
		public BigInteger getUpOrganizationID() {
			return upOrganizationID;
		}
		public void setUpOrganizationID(BigInteger upOrganizationID) {
			this.upOrganizationID = upOrganizationID;
		}
		public Integer getIsLegalEntity() {
			return isLegalEntity;
		}
		public void setIsLegalEntity(Integer isLegalEntity) {
			this.isLegalEntity = isLegalEntity;
		}
		public String getTradingName() {
			return tradingName;
		}
		public void setTradingName(String tradingName) {
			this.tradingName = tradingName;
		}
		public BigInteger getMemberNumber() {
			return memberNumber;
		}
		public void setMemberNumber(BigInteger memberNumber) {
			this.memberNumber = memberNumber;
		}
		public String getPrincipalName() {
			return principalName;
		}
		public void setPrincipalName(String principalName) {
			this.principalName = principalName;
		}
		public String getRegisteredCapital() {
			return registeredCapital;
		}
		public void setRegisteredCapital(String registeredCapital) {
			this.registeredCapital = registeredCapital;
		}
		public String getLegalName() {
			return legalName;
		}
		public void setLegalName(String legalName) {
			this.legalName = legalName;
		}
		public Date getFoundDate() {
			return foundDate;
		}
		public void setFoundDate(Date foundDate) {
			this.foundDate = foundDate;
		}
		public Date getTerminalDate() {
			return terminalDate;
		}
		public void setTerminalDate(Date terminalDate) {
			this.terminalDate = terminalDate;
		}
	    
	    
}
