package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:32:51
 */
@XmlRootElement(name = "response")
public class RegisterResponse extends CommonResponse {

	private RegisterResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public RegisterResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent the responseContent to set
	 */
	public void setResponseContent(RegisterResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}