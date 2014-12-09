package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:03:44
 */
@XmlRootElement(name = "request")
public class DepositRequest extends CommonRequest<DepositRequestBody> {

	private DepositRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public DepositRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent the requestContent to set
	 */
	public void setRequestContent(DepositRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected DepositRequestBody getBody() {
		return requestContent;
	}
}