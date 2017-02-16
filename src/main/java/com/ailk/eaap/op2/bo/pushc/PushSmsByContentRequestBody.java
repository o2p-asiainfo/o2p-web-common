
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushSmsByContentRequestBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushSmsByContentRequestBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NotificationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Port" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TargetAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SmsContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="OpId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Strategy" type="{http://soa.ailk.telenor.com/schema/soa/busi}Strategy" minOccurs="0"/>
 *         &lt;element name="CiInfo" type="{http://soa.ailk.telenor.com/schema/soa/busi}CiInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushSmsByContentRequestBody", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "notificationCode",
    "port",
    "targetAddress",
    "smsContent",
    "customerId",
    "opId",
    "strategy",
    "ciInfo"
})
public class PushSmsByContentRequestBody {

    @XmlElement(name = "NotificationCode")
    protected String notificationCode;
    @XmlElement(name = "Port")
    protected String port;
    @XmlElement(name = "TargetAddress", required = true)
    protected String targetAddress;
    @XmlElement(name = "SmsContent", required = true)
    protected String smsContent;
    @XmlElement(name = "CustomerId")
    protected Long customerId;
    @XmlElement(name = "OpId")
    protected Long opId;
    @XmlElement(name = "Strategy")
    protected Strategy strategy;
    @XmlElement(name = "CiInfo")
    protected CiInfo ciInfo;

    /**
     * Gets the value of the notificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationCode() {
        return notificationCode;
    }

    /**
     * Sets the value of the notificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCode(String value) {
        this.notificationCode = value;
    }

    /**
     * Gets the value of the port property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPort(String value) {
        this.port = value;
    }

    /**
     * Gets the value of the targetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets the value of the targetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetAddress(String value) {
        this.targetAddress = value;
    }

    /**
     * Gets the value of the smsContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * Sets the value of the smsContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsContent(String value) {
        this.smsContent = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerId(Long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the opId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOpId() {
        return opId;
    }

    /**
     * Sets the value of the opId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOpId(Long value) {
        this.opId = value;
    }

    /**
     * Gets the value of the strategy property.
     * 
     * @return
     *     possible object is
     *     {@link Strategy }
     *     
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Sets the value of the strategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Strategy }
     *     
     */
    public void setStrategy(Strategy value) {
        this.strategy = value;
    }

    /**
     * Gets the value of the ciInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CiInfo }
     *     
     */
    public CiInfo getCiInfo() {
        return ciInfo;
    }

    /**
     * Sets the value of the ciInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CiInfo }
     *     
     */
    public void setCiInfo(CiInfo value) {
        this.ciInfo = value;
    }

}
