package com.asiainfo.integration.o2p.session.web.manage;

import com.ailk.eaap.op2.util.SpringContextUtil;

/**
 * @ClassName: SSOUserFactory
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-4 下午7:01:14
 *
 */
public class SSOUserFactory {
	
	
	public static SSOUserCache getDefaultSsoUserCache() {
		SSOUserCache ssoUserCache = (SSOUserCache) SpringContextUtil.getBean("SSORedisUserCache");
		return ssoUserCache;
	}

}
