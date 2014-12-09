package com.yeepay.fpay.rro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:06:46
 */
@XmlRootElement(name = "signature")
public class Signature {

	public final static String DEFAULT_SIGNATURE_TYPE = "MD5";

	protected String signatureType;
	protected String signatureData;

	public Signature() {
		super();
	}

	/**
	 * @param signatureData
	 */
	public Signature(String signatureData) {
		this(DEFAULT_SIGNATURE_TYPE, signatureData);
	}

	/**
	 * @param signatureType
	 * @param signatureData
	 */
	public Signature(String signatureType, String signatureData) {
		super();
		this.signatureType = signatureType;
		this.signatureData = signatureData;
	}

	/**
	 * @return the signatureType
	 */
	public String getSignatureType() {
		return signatureType;
	}

	/**
	 * @param signatureType
	 *            the signatureType to set
	 */
	public void setSignatureType(String signatureType) {
		this.signatureType = signatureType;
	}

	/**
	 * @return the signatureData
	 */
	public String getSignatureData() {
		return signatureData;
	}

	/**
	 * @param signatureData
	 *            the signatureData to set
	 */
	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}
}