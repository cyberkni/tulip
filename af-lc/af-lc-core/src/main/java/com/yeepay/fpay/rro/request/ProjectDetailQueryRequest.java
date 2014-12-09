package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:20:56
 */
@XmlRootElement(name = "request")
public class ProjectDetailQueryRequest extends CommonRequest<ProjectDetailQueryRequestBody> {

	private ProjectDetailQueryRequestBody requestContent;

	/**
	 * @return the requestContent
	 */
	public ProjectDetailQueryRequestBody getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(ProjectDetailQueryRequestBody requestContent) {
		this.requestContent = requestContent;
	}

	@Override
	protected ProjectDetailQueryRequestBody getBody() {
		return requestContent;
	}
}