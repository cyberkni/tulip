package com.yeepay.fpay.rro.response;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月9日 上午10:51:01
 */
public interface ResponseCallback<T extends CommonResponse> {
	boolean validate(T response);
}