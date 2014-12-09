package com.yeepay.fpay.rro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:06:16
 */
@XmlRootElement(name = "topic")
public class Topic {

	public static final Topic DEFAULT_TOPLIC = new Topic();

	public final static String DEFAULT_VERSION = "1.0";
	public final static String DEFAULT_CHARSET = "UTF-8";

	private String version;
	private String charset;

	public Topic() {
		this(DEFAULT_VERSION, DEFAULT_CHARSET);
	}

	/**
	 * @param version
	 * @param charset
	 */
	public Topic(String version, String charset) {
		super();
		this.version = version;
		this.charset = charset;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
}