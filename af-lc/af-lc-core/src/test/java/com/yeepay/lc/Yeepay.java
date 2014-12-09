package com.yeepay.lc;

import java.util.Arrays;

import com.yeepay.fpay.rro.Signature;
import com.yeepay.fpay.rro.Topic;
import com.yeepay.fpay.rro.request.RegisterRequest;
import com.yeepay.fpay.rro.request.RegisterRequestBody;
import com.yeepay.fpay.rro.response.Project;
import com.yeepay.fpay.rro.response.ProjectList;
import com.yeepay.fpay.rro.response.ProjectQueryResponseBody;
import com.yeepay.fpay.rro.response.ProjectQueryResponse;
import com.yeepay.fpay.rro.response.RegisterResponse;
import com.yeepay.fpay.utils.JaxbUtils;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午11:00:27
 */
public class Yeepay {
	
	public static void main(String[] args) throws Exception {
		RegisterRequest request = new RegisterRequest();
        request.setTopic(Topic.DEFAULT_TOPLIC);
        request.setSignature(new Signature("MD5", "dsd"));
        RegisterRequestBody requestContent = new RegisterRequestBody();
        requestContent.setFundChannelId("sdad");
        request.setRequestContent(requestContent);
        System.out.println("parse req : " + JaxbUtils.marshal(request));

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<request>"
				+ "<topic>"
				+ "<version>1.0</version>"
				+ "<charset>utf-8</charset>"
				+ "</topic>"
				+ "<requestContent>"
				+ "<fundChannelId>2</fundChannelId>"
				+ "<loginName>fei.liu</loginName>"
				+ "<password></password>"
				+ "<mobile>2</mobile>"
				+ "<fullName>2</fullName>"
				+ "<idNo>2</idNo>"
				+ "<email>2</email>"
				+ "<bankAccountName></bankAccountName>"
				+ "<branchName></branchName>"
				+ "<bankAccount></bankAccount>"
				+ "<province></province>"
				+ "<city></city>"
				+ "</requestContent>"
				+ "<signature>"
				+ "<signatureType>MD5</signatureType>"
				+ "<signatureData>>CKJSKAFDUAFDIJAAD</signatureData>"
				+ "</signature>"
				+ "</request>";
		RegisterRequest r = JaxbUtils.unmarshal(RegisterRequest.class, xml);
        System.out.println("ss : " + r.getRequestContent().getFundChannelId());
        System.out.println("loginName : " + r.getRequestContent().getLoginName());
        
        RegisterResponse response = new RegisterResponse();
        response.setTopic(Topic.DEFAULT_TOPLIC);
        response.setSignature(new Signature("dsdsd"));
        
        com.yeepay.fpay.rro.response.RegisterResponseBody r1 = new com.yeepay.fpay.rro.response.RegisterResponseBody();
        r1.setErrorCode("223");
        r1.setErrorMessage("dshskdhs");
        response.setResponseContent(r1);
        
        System.out.println("parse response : " + JaxbUtils.marshal(response));
        
        String xmlResp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        		+ "<response>"
        		+ "<topic>"
        		+ "<version>1.0</version>"
        		+ "<charset>utf-8</charset>"
        		+ "</topic>"
        		+ "<responseContent>"
        		+ "<errorCode></errorCode>"
        		+ "<errorMessage></errorMessage>"
        		+ "<userId>1</userId>"
        		+ "</responseContent>"
        		+ "<signature>"
        		+ "<signatureType>MD5</signatureType>"
        		+ "<signatureData>CKJSKAFDUAFDIJAAD</signatureData>"
        		+ "</signature>"
        		+ "</response>";
        
        RegisterResponse rr = JaxbUtils.unmarshal(RegisterResponse.class, xmlResp);
        
        System.out.println("uid : " + rr.getResponseContent().getUserId());
        
        Project p = new Project();
        p.setProjectId("12");
        p.setAmount("323");
        Project p2 = new Project();
        p2.setProjectId("12222");
        p2.setAmount("322223");
        ProjectQueryResponseBody qr = new ProjectQueryResponseBody();
        qr.setProjectCount("1");
        qr.setErrorMessage("121");
        ProjectList pl = new ProjectList();
        pl.setProject(Arrays.asList(p, p2));
        qr.setProjectList(pl);
        ProjectQueryResponse qresp = new ProjectQueryResponse();
        qresp.setResponseContent(qr);
        qresp.setTopic(Topic.DEFAULT_TOPLIC);
        qresp.setSignature(new Signature("23232"));
        
        System.out.println("qr : " + JaxbUtils.marshal(qresp));
        
        String qrespXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        		+ "<response>"
        		+ "<topic><version>1.0</version><charset>utf-8</charset></topic>"
        		+ "<responseContent>"
        		+ "<errorCode>dsd</errorCode>"
        		+ "<errorMessage>sd</errorMessage>"
        		+ "<projectCount>1</projectCount>"
        		+ "<projectList>"
        		
        		+ "<project>"
        		+ "<projectId>1</projectId>"
        		+ "<projectStatus>进行中</projectStatus>"
        		+ "<projectName>测试项目</projectName>"
        		+ "<amount>100000</amount>"
        		+ "<period>6</period>"
        		+ "<interest>10</interest>"
        		+ "<progress>20%</progress>"
        		+ "<amountBalance>5,000.00</amountBalance>"
        		+ "<deadlineDate>2014-08-30</deadlineDate>"
        		+ "</project>"
        		
+ "<project>"
+ "<projectId>2</projectId>"
+ "<projectStatus>进行中2</projectStatus>"
+ "<projectName>测试项目2</projectName>"
+ "<amount>1000002</amount>"
+ "<period>6</period>"
+ "<interest>10</interest>"
+ "<progress>20%</progress>"
+ "<amountBalance>5,000.00</amountBalance>"
+ "<deadlineDate>2014-08-30</deadlineDate>"
+ "</project>"
        		
        		+ "</projectList>"
        		+ "</responseContent>"
        		+ "<signature>"
        		+ "<signatureType>MD5</signatureType>"
        		+ "<signatureData>CKJSKAFDUAFDIJAAD</signatureData>"
        		+ "</signature>"
        		+ "</response>";
        
        ProjectQueryResponse pq = JaxbUtils.unmarshal(ProjectQueryResponse.class, qrespXML);
        
        System.out.println("pid : " + pq.getResponseContent().getProjectList().getProject().get(0).getProjectId());
        
        System.out.println("pid : " + pq.getResponseContent().getProjectList().getProject().get(1).getProjectId());
        
        
	}
}