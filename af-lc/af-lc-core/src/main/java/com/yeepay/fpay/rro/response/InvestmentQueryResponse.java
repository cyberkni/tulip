package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:53:22
 */
@XmlRootElement(name = "response")
public class InvestmentQueryResponse extends CommonResponse {

	private InvestmentQueryResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public InvestmentQueryResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(InvestmentQueryResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}