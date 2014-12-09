package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午12:04:16
 */
@XmlRootElement(name = "requestContent")
public class DepositRequestBody extends CommonRequestBody {

	private String userId;
	private String amount;
	private String depositChannel;
	private String depositType;
	private String externalRef;

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
	 * @return the depositChannel
	 */
	public String getDepositChannel() {
		return depositChannel;
	}

	/**
	 * @param depositChannel
	 *            the depositChannel to set
	 */
	public void setDepositChannel(String depositChannel) {
		this.depositChannel = depositChannel;
	}

	/**
	 * @return the depositType
	 */
	public String getDepositType() {
		return depositType;
	}

	/**
	 * @param depositType
	 *            the depositType to set
	 */
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	/**
	 * @return the externalRef
	 */
	public String getExternalRef() {
		return externalRef;
	}

	/**
	 * @param externalRef
	 *            the externalRef to set
	 */
	public void setExternalRef(String externalRef) {
		this.externalRef = externalRef;
	}

}