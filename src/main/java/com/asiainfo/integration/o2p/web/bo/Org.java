package com.asiainfo.integration.o2p.web.bo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import com.ailk.eaap.op2.bo.OrgOfferOrderRela;

public class Org implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer orgId;
	private Integer filSFileId;
	private Integer sFileId;
	private String orgTypeCode;
	
	//姓名、单位名称
	@Size(min = 4, max = 100, message = "eaap.op2.portal.must4To50Char")
	private String name;
	
	private String orgCode;
	
	//用户名
	@Size(min = 4, max = 100, message = "eaap.op2.portal.must4To50Char")
	private String orgUsername;
	
	private String orgPwd;
	private String certTypeCode;
	private String certNumber;
	private String auditFlowId;
	private String simpleSpell;
	private String state;
	private Date stateTime;
	private Date createTime;
	private String descriptor;
	private String certCopyFilePath;
	private String customerId;
	private String accountId;
	
	//@NotEmpty(message="邮箱不能为空")
	//@Email(message = "eaap.op2.portal.emailValidate")
	private String email;
	
	private String areaId;
	private String address;
	private String certLogo;
	private String telephone;
	private String partnerId;
	private String partnerName;
	private String partnerCode;
	private String firstName;
	private String lastName;
	private String fileIdString;
	private Integer tenantId;
	
	private String partnerType;
	private String subEmail;
	private String subTelephone;
	private Date pwdUpdateDate;
	
	/**
	 * Identification
	 */
	private String indenAddress;
	private String identificationNr;
	private String indenEffDate;
	private String indenExpDate;
	private BigInteger identificationType;
	
	/**
	 * Organization
	 */
    private String legalName;
    
    private String country;
    private Integer partyId;
	private List<OrgOfferOrderRela> orgOfferOrderRelaList;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrgOfferOrderRela> getOrgOfferOrderRelaList() {
		return orgOfferOrderRelaList;
	}

	public void setOrgOfferOrderRelaList(
			List<OrgOfferOrderRela> orgOfferOrderRelaList) {
		this.orgOfferOrderRelaList = orgOfferOrderRelaList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCertLogo() {
		return certLogo;
	}

	public void setCertLogo(String certLogo) {
		this.certLogo = certLogo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeCode() {
		return this.orgTypeCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgUsername(String orgUsername) {
		this.orgUsername = orgUsername;
	}

	public String getOrgUsername() {
		return this.orgUsername;
	}

	public void setOrgPwd(String orgPwd) {
		this.orgPwd = orgPwd;
	}

	public String getOrgPwd() {
		return this.orgPwd;
	}

	public void setCertTypeCode(String certTypeCode) {
		this.certTypeCode = certTypeCode;
	}

	public String getCertTypeCode() {
		return this.certTypeCode;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	public String getCertNumber() {
		return this.certNumber;
	}

	public void setSimpleSpell(String simpleSpell) {
		this.simpleSpell = simpleSpell;
	}

	public String getSimpleSpell() {
		return this.simpleSpell;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}

	public Date getStateTime() {
		return this.stateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return this.descriptor;
	}

	public void setCertCopyFilePath(String certCopyFilePath) {
		this.certCopyFilePath = certCopyFilePath;
	}

	public String getCertCopyFilePath() {
		return this.certCopyFilePath;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public Integer getFilSFileId() {
		return filSFileId;
	}

	public void setFilSFileId(Integer filSFileId) {
		this.filSFileId = filSFileId;
	}

	public Integer getSFileId() {
		return sFileId;
	}

	public void setSFileId(Integer fileId) {
		sFileId = fileId;
	}

	public String getAuditFlowId() {
		return auditFlowId;
	}

	public void setAuditFlowId(String auditFlowId) {
		this.auditFlowId = auditFlowId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getFileIdString() {
		return fileIdString;
	}

	public void setFileIdString(String fileIdString) {
		this.fileIdString = fileIdString;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}

	public String getSubTelephone() {
		return subTelephone;
	}

	public void setSubTelephone(String subTelephone) {
		this.subTelephone = subTelephone;
	}

	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public String getIndenAddress() {
		return indenAddress;
	}

	public void setIndenAddress(String indenAddress) {
		this.indenAddress = indenAddress;
	}

	public String getIdentificationNr() {
		return identificationNr;
	}

	public void setIdentificationNr(String identificationNr) {
		this.identificationNr = identificationNr;
	}

	public String getIndenEffDate() {
		return indenEffDate;
	}

	public void setIndenEffDate(String indenEffDate) {
		this.indenEffDate = indenEffDate;
	}

	public String getIndenExpDate() {
		return indenExpDate;
	}

	public void setIndenExpDate(String indenExpDate) {
		this.indenExpDate = indenExpDate;
	}

	public BigInteger getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(BigInteger identificationType) {
		this.identificationType = identificationType;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	
}
