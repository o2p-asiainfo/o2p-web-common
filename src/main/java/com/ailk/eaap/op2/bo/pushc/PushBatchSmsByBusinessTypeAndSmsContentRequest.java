
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushBatchSmsByBusinessTypeAndSmsContentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushBatchSmsByBusinessTypeAndSmsContentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestBody" type="{http://soa.ailk.telenor.com/schema/soa/busi}PushBatchSmsByBusinessTypeAndSmsContentRequestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushBatchSmsByBusinessTypeAndSmsContentRequest", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "requestBody"
})
public class PushBatchSmsByBusinessTypeAndSmsContentRequest {

    @XmlElement(name = "RequestBody", required = true)
    protected PushBatchSmsByBusinessTypeAndSmsContentRequestBody requestBody;

    /**
     * Gets the value of the requestBody property.
     * 
     * @return
     *     possible object is
     *     {@link PushBatchSmsByBusinessTypeAndSmsContentRequestBody }
     *     
     */
    public PushBatchSmsByBusinessTypeAndSmsContentRequestBody getRequestBody() {
        return requestBody;
    }

    /**
     * Sets the value of the requestBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link PushBatchSmsByBusinessTypeAndSmsContentRequestBody }
     *     
     */
    public void setRequestBody(PushBatchSmsByBusinessTypeAndSmsContentRequestBody value) {
        this.requestBody = value;
    }

}
