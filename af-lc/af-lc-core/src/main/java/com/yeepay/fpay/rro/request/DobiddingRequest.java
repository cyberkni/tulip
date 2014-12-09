package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:11:10
 */
@XmlRootElement(name = "request")
public class DobiddingRequest extends CommonRequest<DobiddingRequestBody> {

	private DobiddingRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public DobiddingRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent the requestContent to set
	 */
	public void setRequestContent(DobiddingRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected DobiddingRequestBody getBody() {
		return requestContent;
	}
}