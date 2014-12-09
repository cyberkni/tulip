package com.yeepay.fpay.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

/**
 * 使用JAXB转换bean对象和XML.
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午6:39:59
 */
@SuppressWarnings("unchecked")
public class JaxbUtils {
	
	/**
	 * 解析XML并组装bean对象。
	 * 
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unmarshal(Class<T> clazz, String xml) throws JAXBException {
		if(clazz == null || StringUtils.isBlank(xml)) {
			return null;
		}
		JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
	}
	
	/**
	 * 解析出bean对应的XML对象。
	 * 
	 * @param object
	 * @return
	 * @throws JAXBException
	 */
	public static <T> String marshal(T object) throws JAXBException {
		if(object == null) {
			return StringUtils.EMPTY;
		}
		JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
		StringWriter sw = new StringWriter();
		marshaller.marshal(object, sw);
		return sw.toString();
	}
}