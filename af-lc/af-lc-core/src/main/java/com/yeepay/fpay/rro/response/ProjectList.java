package com.yeepay.fpay.rro.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:54:05
 */
@XmlRootElement(name = "projectList")
public class ProjectList {
	
	private List<Project> project;

	/**
	 * @return the project
	 */
	public List<Project> getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(List<Project> project) {
		this.project = project;
	}
}