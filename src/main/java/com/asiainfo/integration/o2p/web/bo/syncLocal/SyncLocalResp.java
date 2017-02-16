package com.asiainfo.integration.o2p.web.bo.syncLocal;


public class SyncLocalResp {

	public String transactionID;
	
	public String rspTransactionID;
	
	public String rspTime;
	
	public String rspType;
	
	public String rspCode;
	
	public String rspDesc;

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public void setRspTransactionID(String rspTransactionID) {
		this.rspTransactionID = rspTransactionID;
	}

	public void setRspTime(String rspTime) {
		this.rspTime = rspTime;
	}

	public void setRspType(String rspType) {
		this.rspType = rspType;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getRspTransactionID() {
		return rspTransactionID;
	}

	public String getRspTime() {
		return rspTime;
	}

	public String getRspType() {
		return rspType;
	}

	public String getRspCode() {
		return rspCode;
	}

	public String getRspDesc() {
		return rspDesc;
	}

	
}
