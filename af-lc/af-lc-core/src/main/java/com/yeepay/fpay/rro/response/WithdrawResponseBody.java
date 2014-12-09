package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:08:08
 */
@XmlRootElement(name = "responseContent")
public class WithdrawResponseBody extends CommonResponseBody {

	private String userId;
	private String withdrawId;

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
	 * @return the withdrawId
	 */
	public String getWithdrawId() {
		return withdrawId;
	}

	/**
	 * @param withdrawId
	 *            the withdrawId to set
	 */
	public void setWithdrawId(String withdrawId) {
		this.withdrawId = withdrawId;
	}
}