
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StrategyBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StrategyBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BizSerialNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrategyBody", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "trackFlag",
    "bizSerialNo"
})
public class StrategyBody {

    @XmlElement(name = "TrackFlag", required = true, nillable = true)
    protected String trackFlag;
    @XmlElement(name = "BizSerialNo", required = true, nillable = true)
    protected String bizSerialNo;

    /**
     * Gets the value of the trackFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackFlag() {
        return trackFlag;
    }

    /**
     * Sets the value of the trackFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackFlag(String value) {
        this.trackFlag = value;
    }

    /**
     * Gets the value of the bizSerialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizSerialNo() {
        return bizSerialNo;
    }

    /**
     * Sets the value of the bizSerialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizSerialNo(String value) {
        this.bizSerialNo = value;
    }

}
