/** 
 * Project Name:o2p-web-common-trunk 
 * File Name:RequestBody.java 
 * Package Name:com.ailk.eaap.op2.bo.partnerProfile 
 * Date:2016年9月9日上午9:48:14 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.bo.partnerProfile;  

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * ClassName:RequestBody <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年9月9日 上午9:48:14 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestBody", propOrder = {
    "partyProfile",
})
public class RequestBody {
    @XmlElement(name = "PartyProfile", required = true)
    protected PartyProfile partyProfile;

	public PartyProfile getPartyProfile() {
		return partyProfile;
	}

	public void setPartyProfile(PartyProfile partyProfile) {
		this.partyProfile = partyProfile;
	}
    

}
