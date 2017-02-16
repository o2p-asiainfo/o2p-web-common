package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name="Envelope",namespace = SmsRequestMessage.NAMESPACE_OF_ENVELOPE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope", propOrder = {
    "header",
    "body"
})
public class SmsRequestMessage {
	
	
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
