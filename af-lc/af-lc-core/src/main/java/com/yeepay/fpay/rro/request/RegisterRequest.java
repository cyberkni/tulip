package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:05:16
 */
@XmlRootElement(name = "request")
public class RegisterRequest extends CommonRequest<RegisterRequestBody> {
	
	private RegisterRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public RegisterRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent the requestContent to set
	 */
	public void setRequestContent(RegisterRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected RegisterRequestBody getBody() {
		return requestContent;
	}
}