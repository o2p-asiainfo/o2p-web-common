
package com.ailk.eaap.op2.bo.pushc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushBatchSmsByBusinessTypeAndSmsContentRequestBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushBatchSmsByBusinessTypeAndSmsContentRequestBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Port" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SmsContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OpId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Strategy" type="{http://soa.ailk.telenor.com/schema/soa/busi}Strategy" minOccurs="0"/>
 *         &lt;element name="CiInfo" type="{http://soa.ailk.telenor.com/schema/soa/busi}CiInfo" minOccurs="0"/>
 *         &lt;element name="PushTargetList" type="{http://soa.ailk.telenor.com/schema/soa/busi}PushTargetList" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PushBatchSmsByBusinessTypeAndSmsContentRequestBody", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "businessType",
    "port",
    "smsContent",
    "opId",
    "strategy",
    "ciInfo",
    "pushTargetList"
})
public class PushBatchSmsByBusinessTypeAndSmsContentRequestBody {

    @XmlElement(name = "BusinessType")
    protected long businessType;
    @XmlElement(name = "Port")
    protected String port;
    @XmlElement(name = "SmsContent", required = true)
    protected String smsContent;
    @XmlElement(name = "OpId")
    protected Long opId;
    @XmlElement(name = "Strategy")
    protected Strategy strategy;
    @XmlElement(name = "CiInfo")
    protected CiInfo ciInfo;
    @XmlElement(name = "PushTargetList", required = true)
    protected List<PushTargetList> pushTargetList;

    /**
     * Gets the value of the businessType property.
     * 
     */
    public long getBusinessType() {
        return businessType;
    }

    /**
     * Sets the value of the businessType property.
     * 
     */
    public void setBusinessType(long value) {
        this.businessType = value;
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

    /**
     * Gets the value of the PushTargetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the PushTargetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPushTargetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PushTargetList }
     * 
     * 
     */
    public List<PushTargetList> getPushTargetList() {
        if (pushTargetList == null) {
        	pushTargetList = new ArrayList<PushTargetList>();
        }
        return this.pushTargetList;
    }

	public void setPushTargetList(List<PushTargetList> pushTargetList) {
		this.pushTargetList = pushTargetList;
	}

    
}
