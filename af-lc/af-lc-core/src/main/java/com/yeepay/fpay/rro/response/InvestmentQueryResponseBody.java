package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:53:56
 */
@XmlRootElement(name = "responseContent")
public class InvestmentQueryResponseBody extends CommonResponseBody {

	private String investmentCount;
	private InvestmentList investmentList;

	/**
	 * @return the investmentCount
	 */
	public String getInvestmentCount() {
		return investmentCount;
	}

	/**
	 * @param investmentCount
	 *            the investmentCount to set
	 */
	public void setInvestmentCount(String investmentCount) {
		this.investmentCount = investmentCount;
	}

	/**
	 * @return the investmentList
	 */
	public InvestmentList getInvestmentList() {
		return investmentList;
	}

	/**
	 * @param investmentList
	 *            the investmentList to set
	 */
	public void setInvestmentList(InvestmentList investmentList) {
		this.investmentList = investmentList;
	}
}