/** 
 * Project Name:o2p-web-common-tags 
 * File Name:SoapHeader.java 
 * Package Name:com.ailk.eaap.op2.bo.pushc 
 * Date:2016年3月8日上午10:55:29 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.pushc;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:SoapHeader <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年3月8日 上午10:55:29 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoapHeader", propOrder = {
		"requestHeader"
})
public class SoapHeader {
	public static final String NAMESPACE_OF_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
	public static final String NAMESPACE_OF_SOA ="http://soa.ailk.telenor.com/wsdl/soa";
         
 
        @XmlElement(name = "RequestHeader",namespace =NAMESPACE_OF_SOA)
        protected RequestHeader requestHeader;


		public RequestHeader getRequestHeader() {
			return requestHeader;
		}


		public void setRequestHeader(RequestHeader requestHeader) {
			this.requestHeader = requestHeader;
		}


        
}
