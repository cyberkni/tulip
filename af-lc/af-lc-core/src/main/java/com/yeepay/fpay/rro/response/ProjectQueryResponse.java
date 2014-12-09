package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:28:02
 */
@XmlRootElement(name = "response")
public class ProjectQueryResponse extends CommonResponse {

	private ProjectQueryResponseBody responseContent;

	/**
	 * @return the responseContent
	 */
	public ProjectQueryResponseBody getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent the responseContent to set
	 */
	public void setResponseContent(ProjectQueryResponseBody responseContent) {
		this.responseContent = responseContent;
	}
}