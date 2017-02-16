
package com.ailk.eaap.op2.bo.pushc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Strategy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Strategy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanPushDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrequencyControl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BizSerialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NoteGrade" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RemindGrade" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DndStrategy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Strategy", namespace = "http://soa.ailk.telenor.com/schema/soa/busi", propOrder = {
    "planPushDate",
    "frequencyControl",
    "trackFlag",
    "bizSerialNo",
    "noteGrade",
    "remindGrade",
    "dndStrategy"
})
public class Strategy {

    @XmlElement(name = "PlanPushDate")
    protected String planPushDate;
    @XmlElement(name = "FrequencyControl")
    protected String frequencyControl;
    @XmlElement(name = "TrackFlag")
    protected String trackFlag;
    @XmlElement(name = "BizSerialNo")
    protected String bizSerialNo;
    @XmlElement(name = "NoteGrade")
    protected Long noteGrade;
    @XmlElement(name = "RemindGrade")
    protected Long remindGrade;
    @XmlElement(name = "DndStrategy")
    protected String dndStrategy;

    /**
     * Gets the value of the planPushDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanPushDate() {
        return planPushDate;
    }

    /**
     * Sets the value of the planPushDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanPushDate(String value) {
        this.planPushDate = value;
    }

    /**
     * Gets the value of the frequencyControl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequencyControl() {
        return frequencyControl;
    }

    /**
     * Sets the value of the frequencyControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequencyControl(String value) {
        this.frequencyControl = value;
    }

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

    /**
     * Gets the value of the noteGrade property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNoteGrade() {
        return noteGrade;
    }

    /**
     * Sets the value of the noteGrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNoteGrade(Long value) {
        this.noteGrade = value;
    }

    /**
     * Gets the value of the remindGrade property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemindGrade() {
        return remindGrade;
    }

    /**
     * Sets the value of the remindGrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemindGrade(Long value) {
        this.remindGrade = value;
    }

    /**
     * Gets the value of the dndStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDndStrategy() {
        return dndStrategy;
    }

    /**
     * Sets the value of the dndStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDndStrategy(String value) {
        this.dndStrategy = value;
    }

}
