package com.yeepay.fpay.utils;

import java.nio.charset.Charset;

import org.springframework.util.DigestUtils;

/**
 * MD5签名工具。
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:42:35
 */
public class SignUtils {

	public static String md5Sign(String target, String charset) {
		return DigestUtils.md5DigestAsHex(target.getBytes(Charset.forName(charset)));
	}
}