package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:31:56
 */
@XmlRootElement(name = "project")
public class Project {

	//公共部分
	private String projectId;
	private String projectName;
	
	//以下是项目查询返回
	private String projectStatus;
	private String amount;
	private String period;
	private String interest;
	private String progress;
	private String amountBalance;
	private String deadlineDate;
	
	//以下是项目详情查询部分
	private String borrowerName;
	private String mobile;
	private String idNo;
	private String purpose;
	private String description;
	private String mortgage;
	private String guarantee;
	private String repaymentTypeName;

	/**
	 * @return the borrowerName
	 */
	public String getBorrowerName() {
		return borrowerName;
	}

	/**
	 * @param borrowerName the borrowerName to set
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mortgage
	 */
	public String getMortgage() {
		return mortgage;
	}

	/**
	 * @param mortgage the mortgage to set
	 */
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	/**
	 * @return the guarantee
	 */
	public String getGuarantee() {
		return guarantee;
	}

	/**
	 * @param guarantee the guarantee to set
	 */
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	/**
	 * @return the repaymentTypeName
	 */
	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	/**
	 * @param repaymentTypeName the repaymentTypeName to set
	 */
	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
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
	 * @return the projectStatus
	 */
	public String getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus
	 *            the projectStatus to set
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
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
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the interest
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            the interest to set
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * @return the progress
	 */
	public String getProgress() {
		return progress;
	}

	/**
	 * @param progress
	 *            the progress to set
	 */
	public void setProgress(String progress) {
		this.progress = progress;
	}

	/**
	 * @return the amountBalance
	 */
	public String getAmountBalance() {
		return amountBalance;
	}

	/**
	 * @param amountBalance
	 *            the amountBalance to set
	 */
	public void setAmountBalance(String amountBalance) {
		this.amountBalance = amountBalance;
	}

	/**
	 * @return the deadlineDate
	 */
	public String getDeadlineDate() {
		return deadlineDate;
	}

	/**
	 * @param deadlineDate
	 *            the deadlineDate to set
	 */
	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

}