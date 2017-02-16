
package com.ailk.eaap.op2.bo.pushc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PushBatchEmailByBusinessTypeAndContentRequestBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PushBatchEmailByBusinessTypeAndContentRequestBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="EmailSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmailContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OpId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Strategy" type="{http://soa.ailk.telenor.com/schema/soa/busi}Strategy" minOccurs="0"/>
 *         &lt;element name="CiInfo" type="{http://soa.ailk.telenor.com/schema/soa/busi}CiInfo" minOccurs="0"/>
 *         &lt;element name="AttachFileInfo" type="{http://soa.ailk.telenor.com/schema/soa/busi}AttachFileInfo" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "PushBatchEmailByBusinessTypeAndContentRequestBody", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "businessType",
    "emailSubject",
    "emailContent",
    "opId",
    "strategy",
    "ciInfo",
    "attachFileInfo",
    "pushTargetList"
})
public class PushBatchEmailByBusinessTypeAndContentRequestBody {

    @XmlElement(name = "BusinessType")
    protected long businessType;
    @XmlElement(name = "EmailSubject", required = true)
    protected String emailSubject;
    @XmlElement(name = "EmailContent", required = true)
    protected String emailContent;
    @XmlElement(name = "OpId")
    protected Long opId;
    @XmlElement(name = "Strategy")
    protected Strategy strategy;
    @XmlElement(name = "CiInfo")
    protected CiInfo ciInfo;
    @XmlElement(name = "AttachFileInfo")
    protected List<AttachFileInfo> attachFileInfo;
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
     * Gets the value of the emailSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * Sets the value of the emailSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSubject(String value) {
        this.emailSubject = value;
    }

    /**
     * Gets the value of the emailContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailContent() {
        return emailContent;
    }

    /**
     * Sets the value of the emailContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailContent(String value) {
        this.emailContent = value;
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
     * Gets the value of the AttachFileInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the AttachFileInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachFileInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttachFileInfo }
     * 
     * 
     */
    public List<AttachFileInfo> getAttachFileInfo() {
        if (attachFileInfo == null) {
        	attachFileInfo = new ArrayList<AttachFileInfo>();
        }
        return this.attachFileInfo;
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

	public void setAttachFileInfo(List<AttachFileInfo> attachFileInfo) {
		this.attachFileInfo = attachFileInfo;
	}

	public void setPushTargetList(List<PushTargetList> pushTargetList) {
		this.pushTargetList = pushTargetList;
	}

    
}
