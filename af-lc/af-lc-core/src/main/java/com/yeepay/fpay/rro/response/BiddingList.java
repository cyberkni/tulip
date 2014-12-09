package com.yeepay.fpay.rro.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午1:43:10
 */
@XmlRootElement(name = "biddingList")
public class BiddingList {

	private List<Bidding> bidding;

	/**
	 * @return the bidding
	 */
	public List<Bidding> getBidding() {
		return bidding;
	}

	/**
	 * @param bidding
	 *            the bidding to set
	 */
	public void setBidding(List<Bidding> bidding) {
		this.bidding = bidding;
	}
}