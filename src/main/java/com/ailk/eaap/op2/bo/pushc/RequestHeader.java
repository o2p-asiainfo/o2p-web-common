
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AppKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReqTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccessToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TenantId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptProvCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptChannelType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptChannelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptOrgId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperatorCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AcceptStaffId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceptOpId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DstSysID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeader", propOrder = {
    "appKey",
    "transactionID",
    "reqTime",
    "format",
    "sign",
    "accessToken",
    "version",
    "tenantId",
    "acceptProvCode",
    "acceptRegionCode",
    "acceptChannelType",
    "acceptChannelCode",
    "acceptOrgId",
    "operatorCode",
    "acceptStaffId",
    "acceptOpId",
    "chargeFlag",
    "notifyFlag",
    "dstSysID"
})
public class RequestHeader {

    @XmlElement(name = "AppKey", required = true)
    protected String appKey;
    @XmlElement(name = "TransactionID", required = true)
    protected String transactionID;
    @XmlElement(name = "ReqTime", required = true)
    protected String reqTime;
    @XmlElement(name = "Format")
    protected String format;
    @XmlElement(name = "Sign")
    protected String sign;
    @XmlElement(name = "AccessToken")
    protected String accessToken;
    @XmlElement(name = "Version", required = true)
    protected String version;
    @XmlElement(name = "TenantId")
    protected String tenantId;
    @XmlElement(name = "AcceptProvCode")
    protected String acceptProvCode;
    @XmlElement(name = "AcceptRegionCode")
    protected String acceptRegionCode;
    @XmlElement(name = "AcceptChannelType")
    protected String acceptChannelType;
    @XmlElement(name = "AcceptChannelCode")
    protected String acceptChannelCode;
    @XmlElement(name = "AcceptOrgId")
    protected String acceptOrgId;
    @XmlElement(name = "OperatorCode")
    protected Integer operatorCode;
    @XmlElement(name = "AcceptStaffId")
    protected String acceptStaffId;
    @XmlElement(name = "AcceptOpId")
    protected String acceptOpId;
    @XmlElement(name = "ChargeFlag")
    protected String chargeFlag;
    @XmlElement(name = "NotifyFlag")
    protected String notifyFlag;
    @XmlElement(name = "DstSysID")
    protected String dstSysID;

    /**
     * Gets the value of the appKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * Sets the value of the appKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppKey(String value) {
        this.appKey = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the reqTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqTime() {
        return reqTime;
    }

    /**
     * Sets the value of the reqTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqTime(String value) {
        this.reqTime = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the sign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSign() {
        return sign;
    }

    /**
     * Sets the value of the sign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSign(String value) {
        this.sign = value;
    }

    /**
     * Gets the value of the accessToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the value of the accessToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the tenantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Sets the value of the tenantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantId(String value) {
        this.tenantId = value;
    }

    /**
     * Gets the value of the acceptProvCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptProvCode() {
        return acceptProvCode;
    }

    /**
     * Sets the value of the acceptProvCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptProvCode(String value) {
        this.acceptProvCode = value;
    }

    /**
     * Gets the value of the acceptRegionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptRegionCode() {
        return acceptRegionCode;
    }

    /**
     * Sets the value of the acceptRegionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptRegionCode(String value) {
        this.acceptRegionCode = value;
    }

    /**
     * Gets the value of the acceptChannelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptChannelType() {
        return acceptChannelType;
    }

    /**
     * Sets the value of the acceptChannelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptChannelType(String value) {
        this.acceptChannelType = value;
    }

    /**
     * Gets the value of the acceptChannelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptChannelCode() {
        return acceptChannelCode;
    }

    /**
     * Sets the value of the acceptChannelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptChannelCode(String value) {
        this.acceptChannelCode = value;
    }

    /**
     * Gets the value of the acceptOrgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOrgId() {
        return acceptOrgId;
    }

    /**
     * Sets the value of the acceptOrgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOrgId(String value) {
        this.acceptOrgId = value;
    }

    /**
     * Gets the value of the operatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOperatorCode() {
        return operatorCode;
    }

    /**
     * Sets the value of the operatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOperatorCode(Integer value) {
        this.operatorCode = value;
    }

    /**
     * Gets the value of the acceptStaffId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptStaffId() {
        return acceptStaffId;
    }

    /**
     * Sets the value of the acceptStaffId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptStaffId(String value) {
        this.acceptStaffId = value;
    }

    /**
     * Gets the value of the acceptOpId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptOpId() {
        return acceptOpId;
    }

    /**
     * Sets the value of the acceptOpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptOpId(String value) {
        this.acceptOpId = value;
    }

    /**
     * Gets the value of the chargeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeFlag() {
        return chargeFlag;
    }

    /**
     * Sets the value of the chargeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeFlag(String value) {
        this.chargeFlag = value;
    }

    /**
     * Gets the value of the notifyFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * Sets the value of the notifyFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifyFlag(String value) {
        this.notifyFlag = value;
    }

    /**
     * Gets the value of the dstSysID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstSysID() {
        return dstSysID;
    }

    /**
     * Sets the value of the dstSysID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstSysID(String value) {
        this.dstSysID = value;
    }

}
