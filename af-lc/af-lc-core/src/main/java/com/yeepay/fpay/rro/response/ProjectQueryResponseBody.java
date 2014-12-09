package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:29:11
 */
@XmlRootElement(name = "responseContent")
public class ProjectQueryResponseBody extends CommonResponseBody {

	private String projectCount;

	private ProjectList projectList;

	/**
	 * @return the projectList
	 */
	public ProjectList getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList the projectList to set
	 */
	public void setProjectList(ProjectList projectList) {
		this.projectList = projectList;
	}

	/**
	 * @return the projectCount
	 */
	public String getProjectCount() {
		return projectCount;
	}

	/**
	 * @param projectCount the projectCount to set
	 */
	public void setProjectCount(String projectCount) {
		this.projectCount = projectCount;
	}
}