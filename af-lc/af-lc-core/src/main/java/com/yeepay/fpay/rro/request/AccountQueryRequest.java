package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:25:23
 */
@XmlRootElement(name = "request")
public class AccountQueryRequest extends CommonRequest<AccountQueryRequestBody> {

	private AccountQueryRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public AccountQueryRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent the requestContent to set
	 */
	public void setRequestContent(AccountQueryRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected AccountQueryRequestBody getBody() {
		return requestContent;
	}
}