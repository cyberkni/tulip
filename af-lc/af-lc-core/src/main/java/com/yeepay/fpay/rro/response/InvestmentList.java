package com.yeepay.fpay.rro.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:55:48
 */
@XmlRootElement(name = "investmentList")
public class InvestmentList {

	private List<Investment> investment;

	/**
	 * @return the investment
	 */
	public List<Investment> getInvestment() {
		return investment;
	}

	/**
	 * @param investment
	 *            the investment to set
	 */
	public void setInvestment(List<Investment> investment) {
		this.investment = investment;
	}
}