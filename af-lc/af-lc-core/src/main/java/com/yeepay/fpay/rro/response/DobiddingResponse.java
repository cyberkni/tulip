package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:57:04
 */
@XmlRootElement(name = "response")
public class DobiddingResponse extends CommonResponse {

	private DobiddingResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public DobiddingResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent the responseContent to set
	 */
	public void setResponseContent(DobiddingResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}