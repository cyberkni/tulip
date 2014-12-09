package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:57:33
 */
@XmlRootElement(name = "investment")
public class Investment {

	private String userId;
	private String investmentId;
	private String projectId;
	private String projectName;
	private String periodStartDate;
	private String periodEndDate;
	private String investmentAmount;
	private String totalPayment;
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
	 * @return the investmentId
	 */
	public String getInvestmentId() {
		return investmentId;
	}

	/**
	 * @param investmentId
	 *            the investmentId to set
	 */
	public void setInvestmentId(String investmentId) {
		this.investmentId = investmentId;
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
	 * @return the periodStartDate
	 */
	public String getPeriodStartDate() {
		return periodStartDate;
	}

	/**
	 * @param periodStartDate
	 *            the periodStartDate to set
	 */
	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	/**
	 * @return the periodEndDate
	 */
	public String getPeriodEndDate() {
		return periodEndDate;
	}

	/**
	 * @param periodEndDate
	 *            the periodEndDate to set
	 */
	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	/**
	 * @return the investmentAmount
	 */
	public String getInvestmentAmount() {
		return investmentAmount;
	}

	/**
	 * @param investmentAmount
	 *            the investmentAmount to set
	 */
	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	/**
	 * @return the totalPayment
	 */
	public String getTotalPayment() {
		return totalPayment;
	}

	/**
	 * @param totalPayment
	 *            the totalPayment to set
	 */
	public void setTotalPayment(String totalPayment) {
		this.totalPayment = totalPayment;
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