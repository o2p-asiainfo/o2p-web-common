
package com.ailk.eaap.op2.bo.pushc;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushTargetList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushTargetList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TargetType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="TargetId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TargeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushTargetList", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "targetType",
    "targetId",
    "targeAddress"
})
public class PushTargetList {

    @XmlElement(name = "TargetType", required = true)
    protected BigInteger targetType;
    @XmlElement(name = "TargetId", required = true)
    protected String targetId;
    @XmlElement(name = "TargeAddress", required = true)
    protected String targeAddress;

    /**
     * Gets the value of the targetType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTargetType() {
        return targetType;
    }

    /**
     * Sets the value of the targetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTargetType(BigInteger value) {
        this.targetType = value;
    }

    /**
     * Gets the value of the targetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * Sets the value of the targetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetId(String value) {
        this.targetId = value;
    }

    /**
     * Gets the value of the targeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargeAddress() {
        return targeAddress;
    }

    /**
     * Sets the value of the targeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargeAddress(String value) {
        this.targeAddress = value;
    }

}
