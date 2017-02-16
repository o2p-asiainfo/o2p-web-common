/** 
 * Project Name:o2p-web-common-tags 
 * File Name:EmailRequestMessage.java 
 * Package Name:com.ailk.eaap.op2.bo.pushc 
 * Date:2016年3月8日下午5:33:51 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.pushc;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:EmailRequestMessage <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年3月8日 下午5:33:51 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */

@XmlRootElement(name="Envelope",namespace = EmailRequestMessage.NAMESPACE_OF_ENVELOPE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope", propOrder = {
    "header",
    "body"
})
public class EmailRequestMessage {
	
	public static final String NAMESPACE_OF_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
	public static final String NAMESPACE_OF_SOA ="http://soa.ailk.telenor.com/wsdl/soa";
	
      @XmlElement(name = "Header", namespace = NAMESPACE_OF_ENVELOPE)
      private SoapHeader header;
      @XmlElement(name = "Body", namespace = NAMESPACE_OF_ENVELOPE )
      private SoapBody body;
		public SoapHeader getHeader() {
			return header;
		}
		public void setHeader(SoapHeader header) {
			this.header = header;
		}
		public SoapBody getBody() {
			return body;
		}
		public void setBody(SoapBody body) {
			this.body = body;
		}

}
