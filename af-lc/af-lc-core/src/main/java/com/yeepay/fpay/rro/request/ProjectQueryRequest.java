package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:22:40
 */
@XmlRootElement(name = "request")
public class ProjectQueryRequest extends CommonRequest<ProjectQueryRequestBody> {

	private ProjectQueryRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public ProjectQueryRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent the requestContent to set
	 */
	public void setRequestContent(ProjectQueryRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected ProjectQueryRequestBody getBody() {
		return requestContent;
	}
}