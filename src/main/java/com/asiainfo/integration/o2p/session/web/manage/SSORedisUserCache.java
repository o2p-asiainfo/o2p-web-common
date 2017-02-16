package com.asiainfo.integration.o2p.session.web.manage;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.ailk.eaap.op2.util.SpringContextUtil;
import com.alibaba.fastjson.JSON;
import com.asiainfo.integration.o2p.web.bo.LoginUserInfo;
import com.linkage.rainbow.util.StringUtil;

/**
 * @ClassName: SSORedisUserMgr
 * @Description: 
 * @author zhengpeng
 * @date 2016-9-1 下午5:31:58
 *
 */
@Component(value="SSORedisUserCache")
public class SSORedisUserCache implements SSOUserCache,InitializingBean{
	
	public static final String SSO_USER_PREFIX = "sso_user_";
	public static final String SSO_USER_VAILD_KEY = "sso_user_vaild_key";
	
	private RedisOperations<String,String> userRedisOperations;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		userRedisOperations = this.createDefaultTemplate();
	}
	
	
    private RedisTemplate<String, String> createDefaultTemplate() {
    	RedisConnectionFactory connectionFactory = (RedisConnectionFactory) SpringContextUtil.getBean("redisConnectionFactory");
		RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }
	

	@Override
	public void addUser(String userName,String sessionId) {
		LoginUserInfo userInfo = new LoginUserInfo();
		userInfo.setUserName(userName);
		userInfo.setSessionId(sessionId);
		long loginTime = System.currentTimeMillis();
		userInfo.setLoginTime(loginTime); 
		String userInfoStr = JSON.toJSONString(userInfo);
		String key = SSO_USER_PREFIX + userName;
		userRedisOperations.boundValueOps(key).set(userInfoStr);
		userRedisOperations.boundHashOps(SSO_USER_VAILD_KEY).put(key, String.valueOf(loginTime));
	}

	@Override
	public LoginUserInfo getUser(String userName) {
		String key = SSO_USER_PREFIX + userName;
		String userInfoStr = userRedisOperations.boundValueOps(key).get();
		LoginUserInfo userInfo = null;
		if(!StringUtil.isEmpty(userInfoStr)){
			userInfo = (LoginUserInfo) JSON.parseObject(userInfoStr,LoginUserInfo.class);
		}
		return userInfo; 
	}

	@Override
	public void removeUser(String userName) {
		String key = SSO_USER_PREFIX + userName;
		userRedisOperations.delete(key);
		userRedisOperations.boundHashOps(SSO_USER_VAILD_KEY).delete(key);
	}
	
	@Override
	public void removeKey(String key) {
		userRedisOperations.delete(key);
		userRedisOperations.boundHashOps(SSO_USER_VAILD_KEY).delete(key);
	}


	@Override
	public Map<Object, Object> getUserVaildMap() {
		return userRedisOperations.boundHashOps(SSO_USER_VAILD_KEY).entries();
	}
	
	
	
	

}
