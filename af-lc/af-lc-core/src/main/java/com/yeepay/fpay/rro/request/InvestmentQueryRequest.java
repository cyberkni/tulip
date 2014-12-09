package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:50:00
 */
@XmlRootElement(name = "request")
public class InvestmentQueryRequest extends CommonRequest<InvestmentQueryRequestBody> {

	private InvestmentQueryRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public InvestmentQueryRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(InvestmentQueryRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected InvestmentQueryRequestBody getBody() {
		return requestContent;
	}
}