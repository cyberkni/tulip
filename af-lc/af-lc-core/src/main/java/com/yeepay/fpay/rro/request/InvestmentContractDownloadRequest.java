package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:11:45
 */
@XmlRootElement(name = "request")
public class InvestmentContractDownloadRequest extends CommonRequest<InvestmentContractDownloadRequestBody> {

	private InvestmentContractDownloadRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public InvestmentContractDownloadRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(InvestmentContractDownloadRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected InvestmentContractDownloadRequestBody getBody() {
		return requestContent;
	}
}