package com.yeepay.fpay.rro.response;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午3:16:38
 */
public enum ResultCode {
	
	Unknown("-0001", "未知错误"),

	Success("0000", "操作成功"),
	
	ParametersError("0001", "参数错误"),
	
	SignError("0002", "签名错误"),
	
	ExceptionError("0003", "异常请联系技术人员查看"),
	
	AccessError("0004", "权限不够，请与管理员联系"),
	
	UsernameExists("0101", "用户名已存在"),
	
	MobileExists("0102", "手机号重复"),
	
	NoPublicationProject("0201", "没有发布中的项目"),
	
	DepositOrderNumberNotExists("0301", "充值订单号不存在"),
	
	DepositOrderCancelError("0302", "充值订单已取消"),
	
	DepositAmountError("0303", "充值金额不符"),
	
	ProjectAvailabilityCreditDeficiency("0401", "项目可用额度不足"),
	
	UserAmountInsufficient("0402", "用户金额不足"),
	
	ProjectCompletedTender("0403", "项目已完成投标"),
	
	UserNoExists("0501", "用户不存在"),
	
	UserNoDobidding("0601", "该用户没有投标记录"),
	
	UserNoInvestment("0701", "该用户没有投资记录"),
	
	UserBalanceNotEnough("0801", "用户余额不足"),
	
	NotBindBankCard("0802", "用户没有绑定银行卡"),
	
	BankCardNoBranch("0803", "银行卡没有支行信息")
	
	;
	
	public final String code;
	public final String message;

	private ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}