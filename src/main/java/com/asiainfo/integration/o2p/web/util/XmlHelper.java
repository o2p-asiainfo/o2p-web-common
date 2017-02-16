package com.asiainfo.integration.o2p.web.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * @ClassName: XmlHelper
 * @Description:
 * @author zhengpeng
 * @date 2015-1-30 下午4:39:25
 * 
 */
public class XmlHelper {
	
	@SuppressWarnings("rawtypes") 
	public static String toXML(Object obj,Class className) {
		try {
			JAXBContext context = JAXBContext.newInstance(className);

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
			StringWriter out = new StringWriter();
			OutputFormat format = new OutputFormat();
			format.setIndent(true);
			format.setNewlines(true);
			format.setNewLineAfterDeclaration(false);
			XMLWriter writer = new XMLWriter(out, format);

			XMLFilterImpl nsfFilter = new XMLFilterImpl() {
				private boolean ignoreNamespace = false;
				private String rootNamespace = null;
				private boolean isRootElement = true;

				@Override
				public void startDocument() throws SAXException {
					super.startDocument();
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes atts) throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					if (this.isRootElement)
						this.isRootElement = false;
					else if (!uri.equals("") && !localName.contains("xmlns"))
						localName = localName + " xmlns=\"" + uri + "\"";

					super.startElement(uri, localName, localName, atts);
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (this.ignoreNamespace)
						uri = "";
					super.endElement(uri, localName, localName);
				}

				@Override
				public void startPrefixMapping(String prefix, String url)
						throws SAXException {
					if (this.rootNamespace != null)
						url = this.rootNamespace;
					if (!this.ignoreNamespace)
						super.startPrefixMapping("", url);

				}
			};
			nsfFilter.setContentHandler(writer);
			marshaller.marshal(obj, nsfFilter);
			return out.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<T> valueType) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(valueType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取XML节点内容
	 * 
	 * @param xmldoc
	 *            XML内容
	 * @param XmlNodeName
	 *            节点名称
	 * @param NodeNum
	 *            获取第几个节点里的内容
	 * @return
	 * @throws Exception
	 */
	public static String getXmlNode(String xmldoc, String xmlNodeName,
			int nodeNum) throws Exception {
		XmlString xml = new XmlString();

		xml.xmlNode(xmldoc.getBytes(), xmlNodeName, 0);

		int flag = xml.getFlag();

		StringBuffer sb = new StringBuffer();
		/**
		 * 循环读取所有节点的内容
		 */
		while (flag != 0) {
			sb.append(new String(xml.getConnect()));
			xml.xmlNode(xml.getXml(), xmlNodeName, 0);
			flag = xml.getFlag();
			if (flag == 0) {
				break;
			}
			sb.append(",");
		}
		String xmlStr = sb.toString();

		if (nodeNum != 0) {
			String[] str = xmlStr.split(",");
			if (nodeNum <= str.length && nodeNum >= 0) {
				xmlStr = str[nodeNum - 1];
			}
		}

		return xmlStr;
	}

}
