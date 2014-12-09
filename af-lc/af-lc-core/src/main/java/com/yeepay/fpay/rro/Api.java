package com.yeepay.fpay.rro;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yeepay.fpay.rro.request.AccountQueryRequest;
import com.yeepay.fpay.rro.request.BiddingQueryRequest;
import com.yeepay.fpay.rro.request.CommonRequest;
import com.yeepay.fpay.rro.request.DepositRequest;
import com.yeepay.fpay.rro.request.DobiddingRequest;
import com.yeepay.fpay.rro.request.InvestmentContractDownloadRequest;
import com.yeepay.fpay.rro.request.InvestmentQueryRequest;
import com.yeepay.fpay.rro.request.ProjectDetailQueryRequest;
import com.yeepay.fpay.rro.request.ProjectQueryRequest;
import com.yeepay.fpay.rro.request.RegisterRequest;
import com.yeepay.fpay.rro.request.RegisterRequestBody;
import com.yeepay.fpay.rro.request.WithdrawRequest;
import com.yeepay.fpay.rro.response.AccountQueryResponse;
import com.yeepay.fpay.rro.response.BiddingQueryResponse;
import com.yeepay.fpay.rro.response.DepositResponse;
import com.yeepay.fpay.rro.response.DobiddingResponse;
import com.yeepay.fpay.rro.response.InvestmentContractDownloadResponse;
import com.yeepay.fpay.rro.response.InvestmentQueryResponse;
import com.yeepay.fpay.rro.response.ProjectDetailQueryResponse;
import com.yeepay.fpay.rro.response.ProjectQueryResponse;
import com.yeepay.fpay.rro.response.RegisterResponse;
import com.yeepay.fpay.rro.response.WithdrawResponse;
import com.yeepay.fpay.utils.HttpClientUtils;
import com.yeepay.fpay.utils.JaxbUtils;

/**
 * 易宝金融平台支付API接口抽象。
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 下午3:54:51
 */
public class Api {
	
	private static final Log log = LogFactory.getLog(Api.class);
	
	/**
	 * 项目详细信息查询.
	 * 
	 * @param request
	 * @return
	 */
	public static ProjectDetailQueryResponse projectDetailQuery(ProjectDetailQueryRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.ProjectDetailQuery, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(ProjectDetailQueryResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send ProjectDetailQuery Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse ProjectDetailQuery XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 投资合同下载.
	 * 
	 * @param request
	 * @return
	 */
	public static InvestmentContractDownloadResponse investmentContractDownload(InvestmentContractDownloadRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.InvestmentContractDownload, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(InvestmentContractDownloadResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send InvestmentContractDownload Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse InvestmentContractDownload XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 提现申请
	 * 
	 * @param request
	 * @return
	 */
	public static WithdrawResponse withdraw(WithdrawRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.Withdraw, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(WithdrawResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send Withdraw Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse Withdraw XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 投资记录查询
	 * 
	 * @param request
	 * @return
	 */
	public static InvestmentQueryResponse biddingQuery(InvestmentQueryRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.InvestmentQuery, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(InvestmentQueryResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send InvestmentQuery Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse InvestmentQuery XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 投标记录查询.
	 * 
	 * @param request
	 * @return
	 */
	public static BiddingQueryResponse biddingQuery(BiddingQueryRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.AccountQuery, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(BiddingQueryResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send BiddingQuery Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse BiddingQuery XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 账户信息查询.
	 * 
	 * @param request
	 * @return
	 */
	public static AccountQueryResponse accountQuery(AccountQueryRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.AccountQuery, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(AccountQueryResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send AccountQuery Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse AccountQuery XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 投标.
	 * 
	 * @param request
	 * @return
	 */
	public static DobiddingResponse dobidding(DobiddingRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.Dobidding, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(DobiddingResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send Dobidding Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse Dobidding XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 充值.
	 * 
	 * @param request
	 * @return
	 */
	public static DepositResponse deposit(DepositRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.Deposit, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(DepositResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send Deposit Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse Deposit XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 项目查询.
	 * 
	 * @param request
	 * @return
	 */
	public static ProjectQueryResponse projectQuery(ProjectQueryRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.ProjectQuery, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(ProjectQueryResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send ProjectQuery Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse ProjectQuery XML Error.", e);
		}
		return null;
	}
	
	/**
	 * 投资人注册.
	 * 
	 * @param request
	 * @return
	 */
	public static RegisterResponse register(RegisterRequest request) {
		try {
			String responseBody = Api.sendRequest(ApiURL.Register, request);
			if(StringUtils.isBlank(responseBody)) {
				return null;
			}
			return JaxbUtils.unmarshal(RegisterResponse.class, responseBody);
		} catch (IOException e) {
			log.error("Send Register Request Error.", e);
		} catch (JAXBException e) {
			log.error("Parse Register XML Error.", e);
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		RegisterRequest request = new RegisterRequest();
		request.setTopic(Topic.DEFAULT_TOPLIC);
		RegisterRequestBody requestContent = new RegisterRequestBody();
		requestContent.setFundChannelId("12");
		request.setRequestContent(requestContent);
		String response = sendRequest(ApiURL.Register, request);
		System.out.println(response);
	}

	private static String sendRequest(ApiURL uri, CommonRequest<?> request) throws IOException, JAXBException {
//		return HttpClient.request(uri.prd_uri, JaxbUtils.marshal(request));
		return HttpClientUtils.connectionPost(uri.sendbox_uri, JaxbUtils.marshal(request));
	}
}