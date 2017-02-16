package com.asiainfo.integration.o2p.web.util;

import java.io.IOException;
import java.util.Properties;

import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

/**
 * @ClassName: PropertyUtil
 * @Description: 
 * @author zhengpeng
 * @date 2015-12-7 下午2:04:32
 *
 */
public class PropertyUtil {
	
	private static Logger log = Logger.getLog(PropertyUtil.class);
	
	/**
	 * 获取配置文件中的中文值
	 * 
	 * @param proCode
	 * @return
	 */
	public static String getValueByProCode(String proCode) {
		Properties p = new Properties();
		try {
			p.load(PropertyUtil.class.getResourceAsStream("/eaap_common.properties"));
			return (String) p.get(proCode); 
		} catch (Exception e) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(
					ExceptionCommon.WebExceptionCode,"Gets the configuration file in the Chinese value anomaly",
					null));
			return null;
		}
	}

}
