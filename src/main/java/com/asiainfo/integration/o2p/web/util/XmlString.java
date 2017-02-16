package com.asiainfo.integration.o2p.web.util;

import com.ailk.eaap.op2.common.CommonUtil;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

/**
 * 获取XML节点主体方法
 * @author zhengpeng
 */
public class XmlString {
	private static Logger log = Logger.getLog(XmlString.class);
	//内容
	byte[] connect;
	
	//是否存在节点
	int flag = 1;
	
	//每一个节点之后的XML内容
	byte[] xml;
	
	//通用XML解析（字符数组方法）
	public void xmlNode(byte[] xmldoc, String xmlNodeName,int startPos) throws Exception {
	    try{
	        boolean finded;
	        int p;
	        //XML总长度
	        int p0=xmldoc.length;
	        //头标签长度
	        int p1=xmlNodeName.length()+2;
	        //尾标签长度
	        int p2=p1+1;
	        int k=0;
	        //头标签在XML的坐标
	        int bpos=0;
	        //尾标签在XML的坐标
	        int epos=0;
	        
	        byte[] bTagbytes=new byte[p1];
	        byte[] eTagbytes=new byte[p2];
	        
	        bTagbytes[0]='<';
	        System.arraycopy(xmlNodeName.getBytes(), 0, bTagbytes, 1, xmlNodeName.length());
	        bTagbytes[p1-1]='>';
	        eTagbytes[0]='<';
	        eTagbytes[1]='/';
	        System.arraycopy(xmlNodeName.getBytes(), 0, eTagbytes, 2, xmlNodeName.length());
	        eTagbytes[p1]='>';
	        //查找开始标记
	        finded=false;
	        for(p=startPos;p<p0-p2;p++){
	        	finded=false;
	        	if (xmldoc[p]=='<'){
	        		finded=true;
	        		for(k=0;k<p1;k++){
	        			if (xmldoc[k+p]!=bTagbytes[k]){
	        				finded=false;
	        				break;
	        			}
	       			}
	        	}
	       		if (finded){
	       			bpos=p+p1;
	       			break;
	       		}
	       	}
	        
	       	if (!finded){
				flag = 0;
			}
	       	
	      	//查找结束标记
	       	finded=false;
	       	for(p=bpos;p<p0-p2;p++){
	       		finded=false;
	       		if (xmldoc[p]=='<' && xmldoc[p+1]=='/'){
	       			finded=true;
	       			for(k=0;k<p2;k++){
	       				if (xmldoc[k+p]!=eTagbytes[k]){
	       					finded=false;
	       					break;
	       				}
	       			}
	       		}
	       		if (finded){
	       			epos=p;
	       			break;
	       		}
	       	}
	       	
	       	if (!finded){
				flag = 0;
			}
			byte[] dest = new byte[epos-bpos];
	       	System.arraycopy(xmldoc,bpos,dest,0,epos-bpos);
	       	
	       	connect = dest;
	       	
	       	byte[] xmlStr = new byte[p0-epos-p2];
	       	System.arraycopy(xmldoc,epos+p2,xmlStr,0,p0-epos-p2);
	    	xml = xmlStr;
	        
	    }catch (Exception e){
	    	log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode," XmlString.xmlNode() expectiong:" + CommonUtil.getErrMsg(e),e));
	    	flag = 0;
	    }
	}


	public byte[] getConnect() {
		return connect;
	}

	public void setConnect(byte[] connect) {
		this.connect = connect;
	}

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
