package com.asiainfo.integration.o2p.sqllog.util;

import com.ailk.eaap.o2p.common.util.date.UTCTimeUtil;
import com.linkage.rainbow.util.DateUtil;

/**
 * @ClassName: LocalSqlConver
 * @Description: 
 * @author zhengpeng
 * @date 2015-3-16 上午9:29:21
 *
 */
public class LocalSqlConver {
	
    public static final String MYSQL_UTCDATE = "utc_timestamp";
    public static final String ORACLE_UTCDATE = "SYS_EXTRACT_UTC(current_timestamp)";
    public static final String MYSQL_NOWDATE = "now()";
    
    public static String localSqlConver(String valueSql,String sqlType){
    	String value = "";
    	if(sqlType.equals(SqlParase.MYSQL)){
	    	if(valueSql.toUpperCase().indexOf(MYSQL_UTCDATE.toUpperCase()) > -1){
	    		value = UTCTimeUtil.getUTCDateStrByFormat(UTCTimeUtil.getFullPaterns());
	    	}else if(valueSql.toUpperCase().indexOf(MYSQL_NOWDATE.toUpperCase()) > -1){
	    		value = DateUtil.getNowTime();
	    	}else{
	    		value = valueSql;
	    	}
    	}else if(sqlType.equals(SqlParase.ORACLE)){
    		if(valueSql.toUpperCase().indexOf(ORACLE_UTCDATE.toUpperCase()) > -1){
	    		value = UTCTimeUtil.getUTCDateStrByFormat(UTCTimeUtil.getFullPaterns());
	    	}else{
	    		value = valueSql;
	    	}
    	}
    	return value;
    }
    
}
