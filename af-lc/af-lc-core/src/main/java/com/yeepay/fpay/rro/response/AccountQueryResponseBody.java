package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:28:27
 */
@XmlRootElement(name = "responseContent")
public class AccountQueryResponseBody extends CommonResponseBody {

	private String userId;
	private String totalPayment;
	private String principal;
	private String interestPaid;
	private String interest;

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
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the interestPaid
	 */
	public String getInterestPaid() {
		return interestPaid;
	}

	/**
	 * @param interestPaid
	 *            the interestPaid to set
	 */
	public void setInterestPaid(String interestPaid) {
		this.interestPaid = interestPaid;
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
}