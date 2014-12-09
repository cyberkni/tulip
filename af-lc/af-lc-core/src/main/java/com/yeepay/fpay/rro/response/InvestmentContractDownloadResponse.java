package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:14:46
 */
@XmlRootElement(name = "response")
public class InvestmentContractDownloadResponse extends CommonResponse {

	private InvestmentContractDownloadResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public InvestmentContractDownloadResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(InvestmentContractDownloadResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}