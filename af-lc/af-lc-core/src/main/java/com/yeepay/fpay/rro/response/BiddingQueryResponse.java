package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:40:10
 */
@XmlRootElement(name = "response")
public class BiddingQueryResponse extends CommonResponse {

	private BiddingQueryResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public BiddingQueryResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(BiddingQueryResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}