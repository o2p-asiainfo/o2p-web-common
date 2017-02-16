/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:PartnerProfileRequestMessage.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月25日下午10:06:15 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/** 
 * ClassName:PartnerProfileRequestMessage <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月25日 下午10:06:15 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */

@XmlRootElement(name="Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope", propOrder = {
    "requestHeader",
    "requestBody"
})
public class PartnerProfileRequestMessage {
	
	   @XmlElement(name = "RequestHeader")
	      private RequestHeader requestHeader;
	      @XmlElement(name = "RequestBody")
	      private RequestBody requestBody;
		public RequestHeader getRequestHeader() {
			return requestHeader;
		}
		public void setRequestHeader(RequestHeader requestHeader) {
			this.requestHeader = requestHeader;
		}
		public RequestBody getRequestBody() {
			return requestBody;
		}
		public void setRequestBody(RequestBody requestBody) {
			this.requestBody = requestBody;
		}
	      
	      

}
