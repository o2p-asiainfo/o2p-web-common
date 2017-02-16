
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CiInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CiInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SrcSysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChannelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SrcBusiType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SrcInteractionEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CiInfo", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "srcSysId",
    "channelCode",
    "srcBusiType",
    "srcInteractionEntityId"
})
public class CiInfo {

    @XmlElement(name = "SrcSysId")
    protected String srcSysId;
    @XmlElement(name = "ChannelCode")
    protected String channelCode;
    @XmlElement(name = "SrcBusiType")
    protected String srcBusiType;
    @XmlElement(name = "SrcInteractionEntityId")
    protected String srcInteractionEntityId;

    /**
     * Gets the value of the srcSysId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcSysId() {
        return srcSysId;
    }

    /**
     * Sets the value of the srcSysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcSysId(String value) {
        this.srcSysId = value;
    }

    /**
     * Gets the value of the channelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * Sets the value of the channelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelCode(String value) {
        this.channelCode = value;
    }

    /**
     * Gets the value of the srcBusiType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcBusiType() {
        return srcBusiType;
    }

    /**
     * Sets the value of the srcBusiType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcBusiType(String value) {
        this.srcBusiType = value;
    }

    /**
     * Gets the value of the srcInteractionEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcInteractionEntityId() {
        return srcInteractionEntityId;
    }

    /**
     * Sets the value of the srcInteractionEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcInteractionEntityId(String value) {
        this.srcInteractionEntityId = value;
    }

}
