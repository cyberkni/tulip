package com.yeepay.fpay.rro.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午2:12:13
 */
@XmlRootElement(name = "requestContent")
public class InvestmentContractDownloadRequestBody extends CommonRequestBody {

	private String investmentId;

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

}