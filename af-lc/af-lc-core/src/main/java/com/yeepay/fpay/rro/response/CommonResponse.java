package com.yeepay.fpay.rro.response;

import com.yeepay.fpay.rro.Signature;
import com.yeepay.fpay.rro.Topic;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:29:57
 */
@SuppressWarnings("unchecked")
public class CommonResponse {

	protected Topic topic;
	protected Signature signature;

	/**
	 * 校验数据签名。
	 * 
	 * @param callback
	 * @return
	 */
	public <T extends CommonResponse> boolean validate(ResponseCallback<T> callback) {
		return callback.validate((T) this);
	}
	
	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	/**
	 * @return the signature
	 */
	public Signature getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
}