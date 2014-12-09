package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:07:26
 */
@XmlRootElement(name = "response")
public class WithdrawResponse extends CommonResponse {

	private WithdrawResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public WithdrawResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(WithdrawResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}