package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:28:07
 */
@XmlRootElement(name = "response")
public class AccountQueryResponse extends CommonResponse {

	private AccountQueryResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public AccountQueryResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(AccountQueryResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}