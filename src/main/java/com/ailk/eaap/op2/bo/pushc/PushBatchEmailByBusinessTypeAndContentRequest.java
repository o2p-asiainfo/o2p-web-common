
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushBatchEmailByBusinessTypeAndContentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushBatchEmailByBusinessTypeAndContentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestBody" type="{http://soa.ailk.telenor.com/schema/soa/busi}PushBatchEmailByBusinessTypeAndContentRequestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushBatchEmailByBusinessTypeAndContentRequest", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "requestBody"
})
public class PushBatchEmailByBusinessTypeAndContentRequest {

    @XmlElement(name = "RequestBody", required = true)
    protected PushBatchEmailByBusinessTypeAndContentRequestBody requestBody;

    /**
     * Gets the value of the requestBody property.
     * 
     * @return
     *     possible object is
     *     {@link PushBatchEmailByBusinessTypeAndContentRequestBody }
     *     
     */
    public PushBatchEmailByBusinessTypeAndContentRequestBody getRequestBody() {
        return requestBody;
    }

    /**
     * Sets the value of the requestBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link PushBatchEmailByBusinessTypeAndContentRequestBody }
     *     
     */
    public void setRequestBody(PushBatchEmailByBusinessTypeAndContentRequestBody value) {
        this.requestBody = value;
    }

}
