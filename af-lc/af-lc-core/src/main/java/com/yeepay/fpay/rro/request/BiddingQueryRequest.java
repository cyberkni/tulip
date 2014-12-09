package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:35:58
 */
@XmlRootElement(name = "request")
public class BiddingQueryRequest extends CommonRequest<BiddingQueryRequestBody> {

	private BiddingQueryRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public BiddingQueryRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(BiddingQueryRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected BiddingQueryRequestBody getBody() {
		return requestContent;
	}
}