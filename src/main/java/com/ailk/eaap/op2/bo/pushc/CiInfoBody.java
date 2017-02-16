
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CiInfoBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CiInfoBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SrcSysId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SrcBusiType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CiInfoBody", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "srcSysId",
    "srcBusiType",
    "channelCode"
})
public class CiInfoBody {

    @XmlElement(name = "SrcSysId", required = true, nillable = true)
    protected String srcSysId;
    @XmlElement(name = "SrcBusiType", required = true, nillable = true)
    protected String srcBusiType;
    @XmlElement(name = "ChannelCode", required = true, nillable = true)
    protected String channelCode;

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

}
