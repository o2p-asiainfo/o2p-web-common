package com.ailk.eaap.o2p.common.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName: O2pParametersInterceptor
 * @Description: 
 * @author zhengpeng
 * @date 2015-2-12 上午11:28:42
 *
 */
public class O2pParametersInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> parameters = invocation.getInvocationContext().getParameters();
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")){
			for(Map.Entry<String, Object> paramter : parameters.entrySet()){
				if(paramter.getValue() != null && paramter.getValue() instanceof String[]){
					String[] values = (String[]) paramter.getValue();
					parameters.put(paramter.getKey(), this.transfer(values));
				}
			}
		}
		return invocation.invoke();  
	}
	
	private String[] transfer(String[] params){ 
        for(int i=0;i<params.length;i++){  
            params[i] = xssEncode(params[i]);  
        }  
        return params;  
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
