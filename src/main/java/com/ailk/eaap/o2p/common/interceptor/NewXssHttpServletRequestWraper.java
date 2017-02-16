package com.ailk.eaap.o2p.common.interceptor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

import com.ailk.eaap.o2p.common.util.XssCheckUtil;

public class NewXssHttpServletRequestWraper extends HttpServletRequestWrapper {

	private HttpServletRequest orgRequest ;
	private Map paramMap;
	public NewXssHttpServletRequestWraper(HttpServletRequest request,Map map) {
		super(request);
		this.orgRequest = request;
		this.paramMap = map;
	}

	
	






	@Override
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		//return super.getParameterMap();
		
		
		for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>) iterator.next();
            String [] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                if(values[i] instanceof String){
                	/*if(!XssCheckUtil.xssCheck(values[i])){
                		values[i] = xssEncode(values[i]);
                	}*/
                	
                	values[i] = StringEscapeUtils.escapeHtml(values[i]);
                    
                }
            }
            entry.setValue(values);
        }
		
		
		return paramMap;
	}









	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return super.getInputStream();
	}



/*	private String xssEncode(String para){
		if(para==null || para.equals("")){
			return para;
		}else{
			if(!XssCheckUtil.xssCheck(para)){
				
				return StringEscapeUtils.escapeHtml(para);
			}else{
				return para;
			}
		}
	}*/

	private String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}


	public static  HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof NewXssHttpServletRequestWraper)  {
			
			return ((NewXssHttpServletRequestWraper) req).getOrgRequest();  
		}
		return req;  
	}



	
}
