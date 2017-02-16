package com.asiainfo.integration.o2p.web.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @ClassName: JAXBUtil
 * @Description: 
 * @author zhengpeng
 * @date 2014-12-10 下午5:39:36
 *
 */
public class JAXBUtil {
	
	/**
     * 将对象转为xml字符串，通过JAXB实现
     * @param context
     * @param obj
     * @return
     */
    public static String convertObjToXML(JAXBContext context,Object obj){
    	return JAXBUtil.convertObjToXMLByParam(context, obj, true);
    }
    
    public static String convertObjToXMLByParam(JAXBContext context,Object obj,boolean isHead){
        String dataXML = "";
        try {
            Marshaller marshaller = context.createMarshaller();
            //编码格式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //是否省略xml头信息
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            dataXML = writer.toString();
        } catch (JAXBException e) {
        }
        return dataXML;
    }
    

    public static String convertObjToXMLUnHead(JAXBContext context,Object obj){
        return JAXBUtil.convertObjToXMLByParam(context, obj, false);
    }
    
    
    
    

}
