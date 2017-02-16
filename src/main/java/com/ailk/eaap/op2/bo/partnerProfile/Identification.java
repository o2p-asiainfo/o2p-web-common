/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:Identification.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月9日上午10:09:14 
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
 * ClassName:Identification <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月9日 上午10:09:14 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Identification", propOrder = {
    "indenAddress",
    "identificationNr",
    "indenEffDate",
    "indenExpDate",
    "identificationType"
})
public class Identification {
    @XmlElement(name = "IndenAddress")
    protected String indenAddress;
    @XmlElement(name = "IdentificationNr", required = true)
    protected String identificationNr;
    @XmlElement(name = "IndenEffDate", required = true)
    protected String indenEffDate;
    @XmlElement(name = "IndenExpDate", required = true)
    protected String indenExpDate;
    @XmlElement(name = "IdentificationType", required = true)
    protected BigInteger identificationType;
	public String getIndenAddress() {
		return indenAddress;
	}
	public void setIndenAddress(String indenAddress) {
		this.indenAddress = indenAddress;
	}
	public String getIdentificationNr() {
		return identificationNr;
	}
	public void setIdentificationNr(String identificationNr) {
		this.identificationNr = identificationNr;
	}
	public String getIndenEffDate() {
		return indenEffDate;
	}
	public void setIndenEffDate(String indenEffDate) {
		this.indenEffDate = indenEffDate;
	}
	public String getIndenExpDate() {
		return indenExpDate;
	}
	public void setIndenExpDate(String indenExpDate) {
		this.indenExpDate = indenExpDate;
	}
	public BigInteger getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(BigInteger identificationType) {
		this.identificationType = identificationType;
	}
    
    
    

}
