package com.asiainfo.integration.o2p.session.web.scheduled;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asiainfo.integration.o2p.session.web.manage.SSOUserFactory;
import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;
import com.linkage.rainbow.util.StringUtil;

/**
 * @ClassName: SSORedisUserScheduled
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-6 下午4:29:36
 *
 */
@Component(value="SSOUserScheduled")
public class SSOUserScheduled {
	
	public static final String SSO_USER_VAILD_TIME = "sso_user_vaild_time";
	
	/**
	 * 定时器,检查用户是否已登录系统8小时，则中断用户会话，要求用户重新登录
	 */
	@Scheduled(cron="0 0/1 * * * ?")
	public void verifyUserVaild(){
		Map<Object,Object> userVaildMap = SSOUserFactory.getDefaultSsoUserCache().getUserVaildMap();
		if(userVaildMap != null && !userVaildMap.isEmpty()){
			for(Map.Entry<Object,Object> entry : userVaildMap.entrySet()){
				String key = (String) entry.getKey();
				long loginTime = Long.valueOf((String)entry.getValue());
				long currentTime = System.currentTimeMillis();
				long timeDiffer = (currentTime - loginTime)/1000;
				long vaildTime = this.getVaildTime();
				if(timeDiffer > vaildTime){
					SSOUserFactory.getDefaultSsoUserCache().removeKey(key);
				}
			}
		}
	}
	
	private long getVaildTime(){
		long vaildTime = 28800;
		if(!StringUtil.isEmpty(WebPropertyUtil.getPropertyValue(SSO_USER_VAILD_TIME))){
			vaildTime = Long.valueOf(WebPropertyUtil.getPropertyValue(SSO_USER_VAILD_TIME)); 
		}
		return vaildTime;
	}

}
