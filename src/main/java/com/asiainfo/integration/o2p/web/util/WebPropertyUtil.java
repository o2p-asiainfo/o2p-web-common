package com.asiainfo.integration.o2p.web.util;

import com.ailk.eaap.o2p.common.spring.config.CustomPropertyConfigurer;
import com.ailk.eaap.o2p.common.spring.config.ZKCfgCacheHolder;
import com.ailk.eaap.o2p.common.util.CommonUtil;
import com.linkage.rainbow.util.StringUtil;

/**
 * @ClassName: WebPropertyUtil
 * @Description: 
 * @author zhengpeng
 * @date 2016-2-16 下午2:55:01
 *
 */
public class WebPropertyUtil {
	
//	public static String getPropertyValue(String propertyKey){
//		return CustomPropertyConfigurer.getProperty(propertyKey);
//	}
	
	
	public static String getPropertyValue(String propertyKey){
		return CommonUtil.getPropertyValue(propertyKey);
	}

}
