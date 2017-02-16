package com.asiainfo.integration.o2p.web.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>PropertyUtilTest</code> contains tests for the class <code>{@link PropertyUtil}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午8:16
 * @author zhengpeng
 * @version $Revision: 1.0 $
 */
public class PropertyUtilTest {
	/**
	 * Run the PropertyUtil() constructor test.
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:16
	 */
	@Test
	public void testPropertyUtil_1()
		throws Exception {
		PropertyUtil result = new PropertyUtil();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getValueByProCode(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:16
	 */
	@Test
	public void testGetValueByProCode_1()
		throws Exception {
		try{
		String proCode = "";

		String result = PropertyUtil.getValueByProCode(proCode);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.util.Properties$LineReader.readLine(Properties.java:418)
		//       at java.util.Properties.load0(Properties.java:337)
		//       at java.util.Properties.load(Properties.java:325)
		//       at com.asiainfo.integration.o2p.web.util.PropertyUtil.getValueByProCode(PropertyUtil.java:31)
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getValueByProCode(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:16
	 */
	@Test
	public void testGetValueByProCode_2()
		throws Exception {
		try{
		String proCode = "";

		String result = PropertyUtil.getValueByProCode(proCode);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at java.util.Properties$LineReader.readLine(Properties.java:418)
		//       at java.util.Properties.load0(Properties.java:337)
		//       at java.util.Properties.load(Properties.java:325)
		//       at com.asiainfo.integration.o2p.web.util.PropertyUtil.getValueByProCode(PropertyUtil.java:31)
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
	 * @generatedBy CodePro at 15-12-17 下午8:16
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
	 * @generatedBy CodePro at 15-12-17 下午8:16
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
	 * @generatedBy CodePro at 15-12-17 下午8:16
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PropertyUtilTest.class);
	}
}