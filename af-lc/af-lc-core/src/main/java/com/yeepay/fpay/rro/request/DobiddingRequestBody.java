package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:11:39
 */
@XmlRootElement(name = "requestContent")
public class DobiddingRequestBody extends CommonRequestBody {

	private String userId;
	private String biddingAmount;
	private String projectId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the biddingAmount
	 */
	public String getBiddingAmount() {
		return biddingAmount;
	}

	/**
	 * @param biddingAmount
	 *            the biddingAmount to set
	 */
	public void setBiddingAmount(String biddingAmount) {
		this.biddingAmount = biddingAmount;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}