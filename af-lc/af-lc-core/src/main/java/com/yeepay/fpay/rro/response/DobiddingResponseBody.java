package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:58:10
 */
@XmlRootElement(name = "responseContent")
public class DobiddingResponseBody extends CommonResponseBody {

	private String userId;
	private String projectId;
	private String biddingAmount;
	private String amountBalance;

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

}