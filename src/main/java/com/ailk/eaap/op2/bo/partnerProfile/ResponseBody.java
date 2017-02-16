/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:ResponseBody.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月22日下午10:53:15 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:ResponseBody <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月22日 下午10:53:15 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlRootElement(name="ResponseBody")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBody", propOrder = {
    "partyRoleID",
    "partyID",
    "orderDateTime",
    "resultCode",
    "resultMessage"
})
public class ResponseBody {
	@XmlElement(name = "PartyRoleID")
    protected Integer partyRoleID;
	@XmlElement(name = "PartyID")
	protected Integer partyID;
	@XmlElement(name = "OrderDateTime")
	protected Date orderDateTime;
	@XmlElement(name = "ResultCode")
	protected String resultCode;
	@XmlElement(name = "ResultMessage")
	protected String resultMessage;
	public Integer getPartyRoleID() {
		return partyRoleID;
	}
	public void setPartyRoleID(Integer partyRoleID) {
		this.partyRoleID = partyRoleID;
	}
	public  Integer getPartyID() {
		return partyID;
	}
	public void setPartyID(Integer partyID) {
		this.partyID = partyID;
	}
	public Date getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	
	
	 
}
