package com.ailk.eaap.o2p.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName: XssHttpServletRequestWraper
 * @Description:
 * @author zhengpeng
 * @date 2015-2-3 上午9:51:30
 * 
 */
public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

	private HttpServletRequest request = null;

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value != null) {
			//如果是get,转义
			if(this.request.getMethod().equalsIgnoreCase("get")){
				value = this.xssEncode(value);
			}
		}
		return value;
	}


	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
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

}
