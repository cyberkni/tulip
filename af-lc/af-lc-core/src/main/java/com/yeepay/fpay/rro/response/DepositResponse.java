package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:07:21
 */
@XmlRootElement(name = "response")
public class DepositResponse extends CommonResponse {

	private DepositResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public DepositResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent the responseContent to set
	 */
	public void setResponseContent(DepositResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}