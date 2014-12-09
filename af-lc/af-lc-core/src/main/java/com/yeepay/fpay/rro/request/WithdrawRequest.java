package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:04:44
 */
@XmlRootElement(name = "request")
public class WithdrawRequest extends CommonRequest<WithdrawRequestBody> {

	private WithdrawRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public WithdrawRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(WithdrawRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected WithdrawRequestBody getBody() {
		return requestContent;
	}
}