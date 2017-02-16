/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:ContactInformation.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月9日上午10:09:53 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:ContactInformation <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月9日 上午10:09:53 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactInformation", propOrder = {
    "emailID",
    "mobileNumber"
})
public class ContactInformation {
	@XmlElement(name = "EmailID")
    protected String emailID;
	@XmlElement(name = "MobileNumber")
    protected String mobileNumber;
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
