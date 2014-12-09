package com.yeepay.fpay.rro;

/**
 * 易宝金融支付平台相关URL资源。
 * 
 * 测试环境：http://172.17.253.118:8999/api
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午9:57:40
 */
public enum ApiURL {

	Register("http://api.yeepbank.com/investor/register", "http://172.17.253.118:8999/api/investor/register", "投资人注册"),
	
	ProjectQuery("http://api.yeepbank.com/project/query", "http://172.17.253.118:8999/api/project/query", "项目查询"),
	
	Deposit("http//api.yeepbank.com/investor/deposit", "http://172.17.253.118:8999/api/investor/deposit", "充值"),
	
	Dobidding("http//api.yeepbank.com/bidding/dobidding", "http://172.17.253.118:8999/api/bidding/dobidding", "投标"),
	
	AccountQuery("http//api.yeepbank.com/investor/query", "http://172.17.253.118:8999/api/investor/query", "账户信息查询"),
	
	BiddingQuery("http//api.yeepbank.com/bidding/query", "http://172.17.253.118:8999/api/bidding/query", "投标记录查询"),
	
	InvestmentQuery("http//api.yeepbank.com/investment/query", "http://172.17.253.118:8999/api/investment/query", "投资记录查询"),
	
	Withdraw("http//api.yeepbank.com/investor/withdraw", "http://172.17.253.118:8999/api/investor/withdraw", "提现申请"),
	
	InvestmentContractDownload("http//api.yeepbank.com/investment/investmentContractDownload", "http://172.17.253.118:8999/api/investment/investmentContractDownload", "投资合同下载"),
	
	ProjectDetailQuery("http://api.yeepbank.com/project/getProjectInfo", "http://172.17.253.118:8999/api/project/getProjectInfo", "项目详细信息查询")
	
	;
	public final String prd_uri;
	public final String sendbox_uri;
	public final String description;
	
	private ApiURL(String prd_uri, String sendbox_uri, String description) {
		this.prd_uri = prd_uri;
		this.sendbox_uri = sendbox_uri;
		this.description = description;
	}
}