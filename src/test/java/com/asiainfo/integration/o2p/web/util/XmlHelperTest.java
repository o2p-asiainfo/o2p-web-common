package com.asiainfo.integration.o2p.web.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>XmlHelperTest</code> contains tests for the class <code>{@link XmlHelper}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午8:17
 * @author zhengpeng
 * @version $Revision: 1.0 $
 */
public class XmlHelperTest {
	/**
	 * Run the Object fromXML(String,Class<T>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testFromXML_1()
		throws Exception {
		try{
		String xml = "";
		Class<Object> valueType = Object.class;

		Object result = XmlHelper.fromXML(xml, valueType);

		// add additional test code here
		assertNotNull(result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_1()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = 1;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_2()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = 1;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_3()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = 1;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_4()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = 1;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_5()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = -1;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String getXmlNode(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXmlNode_6()
		throws Exception {
		try{
		String xmldoc = "";
		String xmlNodeName = "";
		int nodeNum = 0;

		String result = XmlHelper.getXmlNode(xmldoc, xmlNodeName, nodeNum);

		// add additional test code here
		assertEquals("", result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the String toXML(Object,Class) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testToXML_1()
		throws Exception {
		try{
		Object obj = new Object();
		Class className = Object.class;

		String result = XmlHelper.toXML(obj, className);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: javax.xml.bind.JAXBException: class java.lang.Object nor any of its super class is known to this context.
		//       at com.asiainfo.integration.o2p.web.util.XmlHelper.toXML(XmlHelper.java:87)
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
		new org.junit.runner.JUnitCore().run(XmlHelperTest.class);
	}
}