/** 
 * Project Name:o2p-web-common-tags 
 * File Name:SoapBody.java 
 * Package Name:com.ailk.eaap.op2.bo.pushc 
 * Date:2016年3月8日上午10:56:43 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.pushc;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:SoapBody <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年3月8日 上午10:56:43 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoapBody", propOrder = {
		"pushSmsByContentRequestBody",
		"pushEmailByContentRequestBody",
		"pushBatchEmailByBusinessTypeAndContentRequestBody",
		"pushBatchSmsByBusinessTypeAndSmsContentRequestBody"
})
public class SoapBody {
	public static final String NAMESPACE_OF_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
	public static final String NAMESPACE_OF_SOA ="http://soa.ailk.telenor.com/wsdl/soa";
         
 
        @XmlElement(name = "pushSmsByContentRequest",namespace=NAMESPACE_OF_SOA)
        protected PushSmsByContentRequest pushSmsByContentRequestBody;

        @XmlElement(name = "pushEmailByContentRequest",namespace=NAMESPACE_OF_SOA)
        protected PushEmailByContentRequest pushEmailByContentRequestBody;
        
        @XmlElement(name = "pushBatchEmailByBusinessTypeAndContentRequest",namespace=NAMESPACE_OF_SOA)
        protected PushBatchEmailByBusinessTypeAndContentRequest pushBatchEmailByBusinessTypeAndContentRequestBody;
        
        @XmlElement(name = "pushBatchSmsByBusinessTypeAndSmsContentRequest",namespace=NAMESPACE_OF_SOA)
        protected PushBatchSmsByBusinessTypeAndSmsContentRequest pushBatchSmsByBusinessTypeAndSmsContentRequestBody;
        
		public PushSmsByContentRequest getPushSmsByContentRequestBody() {
			return pushSmsByContentRequestBody;
		}
		public void setPushSmsByContentRequestBody(
				PushSmsByContentRequest pushSmsByContentRequestBody) {
			this.pushSmsByContentRequestBody = pushSmsByContentRequestBody;
		}
		public PushEmailByContentRequest getPushEmailByContentRequestBody() {
			return pushEmailByContentRequestBody;
		}
		public void setPushEmailByContentRequestBody(
				PushEmailByContentRequest pushEmailByContentRequestBody) {
			this.pushEmailByContentRequestBody = pushEmailByContentRequestBody;
		}
		public PushBatchEmailByBusinessTypeAndContentRequest getPushBatchEmailByBusinessTypeAndContentRequestBody() {
			return pushBatchEmailByBusinessTypeAndContentRequestBody;
		}
		public void setPushBatchEmailByBusinessTypeAndContentRequestBody(
				PushBatchEmailByBusinessTypeAndContentRequest pushBatchEmailByBusinessTypeAndContentRequestBody) {
			this.pushBatchEmailByBusinessTypeAndContentRequestBody = pushBatchEmailByBusinessTypeAndContentRequestBody;
		}
		public PushBatchSmsByBusinessTypeAndSmsContentRequest getPushBatchSmsByBusinessTypeAndSmsContentRequestBody() {
			return pushBatchSmsByBusinessTypeAndSmsContentRequestBody;
		}
		public void setPushBatchSmsByBusinessTypeAndSmsContentRequestBody(
				PushBatchSmsByBusinessTypeAndSmsContentRequest pushBatchSmsByBusinessTypeAndSmsContentRequestBody) {
			this.pushBatchSmsByBusinessTypeAndSmsContentRequestBody = pushBatchSmsByBusinessTypeAndSmsContentRequestBody;
		}

}
