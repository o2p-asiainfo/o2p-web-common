package com.asiainfo.integration.o2p.web.util;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>HttpClientUtilTest</code> contains tests for the class <code>{@link HttpClientUtil}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午8:17
 * @author zhengpeng
 * @version $Revision: 1.0 $
 */
public class HttpClientUtilTest {
	/**
	 * Run the HttpClientUtil() constructor test.
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testHttpClientUtil_1()
		throws Exception {
		HttpClientUtil result = new HttpClientUtil();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String sendRequest(String,String,String,int,String,String,String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSendRequest_1()
		throws Exception {
		try{
		String address = "";
		String msg = "";
		String method = "";
		int timeout = 1;
		String serviceName = "";
		String userName = "";
		String password = "";
		String proxyIp = "";
		String proxyPort = "";

		String result = HttpClientUtil.sendRequest(address, msg, method, timeout, serviceName, userName, password, proxyIp, proxyPort);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    <exception><code>9998</code><msg>null</msg><exceptionTrace>com.asiainfo.foundation.exception.BusinessException
		//       at com.asiainfo.integration.o2p.web.util.HttpClientUtil.sendRequest(HttpClientUtil.java:123)
		//       at com.asiainfo.integration.o2p.web.util.HttpClientUtil.sendRequest(HttpClientUtil.java:36)
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String sendRequest(String,Map<String,String>,String,String,String,int,String,String,String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSendRequest_2()
		throws Exception {
		try{
		String address = "a";
		Map<String, String> reqHead = new HashMap();
		String msg = "a";
		String contentType = "a";
		String method = "a";
		int timeout = 1;
		String servicename = "a";
		String userName = "";
		String password = "a";
		String proxyIP = "";
		String proxyPort = "0";

		String result = HttpClientUtil.sendRequest(address, reqHead, msg, contentType, method, timeout, servicename, userName, password, proxyIP, proxyPort);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    <exception><code>9998</code><msg>null</msg><exceptionTrace>com.asiainfo.foundation.exception.BusinessException
		//       at com.asiainfo.integration.o2p.web.util.HttpClientUtil.sendRequest(HttpClientUtil.java:123)
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String sendRequest(String,Map<String,String>,String,String,String,int,String,String,String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSendRequest_3()
		throws Exception {
		try{
		String address = "";
		Map<String, String> reqHead = new HashMap();
		String msg = null;
		String contentType = "";
		String method = "";
		int timeout = 1;
		String servicename = "";
		String userName = "";
		String password = "";
		String proxyIP = "";
		String proxyPort = "";

		String result = HttpClientUtil.sendRequest(address, reqHead, msg, contentType, method, timeout, servicename, userName, password, proxyIP, proxyPort);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    <exception><code>9998</code><msg>null</msg><exceptionTrace>com.asiainfo.foundation.exception.BusinessException
		//       at com.asiainfo.integration.o2p.web.util.HttpClientUtil.sendRequest(HttpClientUtil.java:123)
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String sendRequest(String,Map<String,String>,String,String,String,int,String,String,String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSendRequest_4()
		throws Exception {
		try{
		String address = "";
		Map<String, String> reqHead = null;
		String msg = "";
		String contentType = "";
		String method = "";
		int timeout = 1;
		String servicename = "";
		String userName = null;
		String password = "";
		String proxyIP = null;
		String proxyPort = "";

		String result = HttpClientUtil.sendRequest(address, reqHead, msg, contentType, method, timeout, servicename, userName, password, proxyIP, proxyPort);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    <exception><code>9998</code><msg>null</msg><exceptionTrace>com.asiainfo.foundation.exception.BusinessException
		//       at com.asiainfo.integration.o2p.web.util.HttpClientUtil.sendRequest(HttpClientUtil.java:123)
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(HttpClientUtilTest.class);
	}
}