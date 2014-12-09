package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:43:53
 */
@XmlRootElement(name = "bidding")
public class Bidding {

	private String userId;
	private String serialNo;
	private String projectId;
	private String projectName;
	private String biddingDatetime;
	private String appoveDatetime;
	private String biddingAmount;
	private String status;

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
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the biddingDatetime
	 */
	public String getBiddingDatetime() {
		return biddingDatetime;
	}

	/**
	 * @param biddingDatetime
	 *            the biddingDatetime to set
	 */
	public void setBiddingDatetime(String biddingDatetime) {
		this.biddingDatetime = biddingDatetime;
	}

	/**
	 * @return the appoveDatetime
	 */
	public String getAppoveDatetime() {
		return appoveDatetime;
	}

	/**
	 * @param appoveDatetime
	 *            the appoveDatetime to set
	 */
	public void setAppoveDatetime(String appoveDatetime) {
		this.appoveDatetime = appoveDatetime;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}