package com.asiainfo.integration.o2p.web.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>XmlStringTest</code> contains tests for the class <code>{@link XmlString}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午8:17
 * @author zhengpeng
 * @version $Revision: 1.0 $
 */
public class XmlStringTest {
	/**
	 * Run the XmlString() constructor test.
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlString_1()
		throws Exception {
		XmlString result = new XmlString();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the byte[] getConnect() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetConnect_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});

		byte[] result = fixture.getConnect();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.length);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the int getFlag() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetFlag_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});

		int result = fixture.getFlag();

		// add additional test code here
		assertEquals(1, result);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the byte[] getXml() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testGetXml_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});

		byte[] result = fixture.getXml();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.length);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void setConnect(byte[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSetConnect_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] connect = new byte[] {};

		fixture.setConnect(connect);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void setFlag(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSetFlag_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		int flag = 1;

		fixture.setFlag(flag);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void setXml(byte[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testSetXml_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xml = new byte[] {};

		fixture.setXml(xml);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_1()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 47};
		String xmlNodeName = "1";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_2()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0};
		String xmlNodeName = "2";
		int startPos = 2;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_3()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 47};
		String xmlNodeName = "3";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_4()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "4";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_5()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0};
		String xmlNodeName = "5";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_6()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "6";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_7()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "7";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_8()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 1};
		String xmlNodeName = "8";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_9()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 1};
		String xmlNodeName = "9";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_10()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 1};
		String xmlNodeName = "10";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_11()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 1};
		String xmlNodeName = "11";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_12()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 60, (byte) 1};
		String xmlNodeName = "12";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);

		// add additional test code here
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_13()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "13";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);
		}catch (Exception e) {
			// TODO: handle exception
		}

		// add additional test code here
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_14()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "14";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);
		}catch (Exception e) {
			// TODO: handle exception
		}

		// add additional test code here
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_15()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "15";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);
		}catch (Exception e) {
			// TODO: handle exception
		}

		// add additional test code here
	}

	/**
	 * Run the void xmlNode(byte[],String,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-12-17 下午8:17
	 */
	@Test
	public void testXmlNode_16()
		throws Exception {
		try{
		XmlString fixture = new XmlString();
		fixture.setXml(new byte[] {});
		fixture.setFlag(1);
		fixture.setConnect(new byte[] {});
		byte[] xmldoc = new byte[] {(byte) 0, (byte) 0};
		String xmlNodeName = "16";
		int startPos = 1;

		fixture.xmlNode(xmldoc, xmlNodeName, startPos);
		}catch (Exception e) {
			// TODO: handle exception
		}

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(XmlStringTest.class);
	}
}