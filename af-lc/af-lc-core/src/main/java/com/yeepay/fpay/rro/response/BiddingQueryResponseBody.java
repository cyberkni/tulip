package com.yeepay.fpay.rro.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:40:37
 */
@XmlRootElement(name = "responseContent")
public class BiddingQueryResponseBody extends CommonResponseBody {

	private String biddingCount;
	private BiddingList biddingList;

	/**
	 * @return the biddingCount
	 */
	public String getBiddingCount() {
		return biddingCount;
	}

	/**
	 * @param biddingCount
	 *            the biddingCount to set
	 */
	public void setBiddingCount(String biddingCount) {
		this.biddingCount = biddingCount;
	}

	/**
	 * @return the biddingList
	 */
	public BiddingList getBiddingList() {
		return biddingList;
	}

	/**
	 * @param biddingList
	 *            the biddingList to set
	 */
	public void setBiddingList(BiddingList biddingList) {
		this.biddingList = biddingList;
	}
}