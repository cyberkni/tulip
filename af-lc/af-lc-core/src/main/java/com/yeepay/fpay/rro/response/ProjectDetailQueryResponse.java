package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:23:33
 */
@XmlRootElement(name = "response")
public class ProjectDetailQueryResponse extends CommonResponse {

	private ProjectDetailQueryResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public ProjectDetailQueryResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(ProjectDetailQueryResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}