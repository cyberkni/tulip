package com.yeepay.fpay.rro.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yeepay.fpay.rro.Signature;
import com.yeepay.fpay.rro.Topic;
import com.yeepay.fpay.utils.JaxbUtils;
import com.yeepay.fpay.utils.SignUtils;

/**
 * 
 * @author john.liu E-mail:fei.liu@yeepay.com
 * @version 1.0.0
 * @since 2014年12月8日 下午3:03:47
 */
public abstract class CommonRequest<T extends CommonRequestBody> {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected Topic topic;
	protected Signature signature;
	
	protected abstract T getBody();
	
	public void md5Sign(String key) {
		try {
			String bodyXML = JaxbUtils.marshal(getBody()).replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			bodyXML += ("key=" + key);
			signature = new Signature(SignUtils.md5Sign(bodyXML, Topic.DEFAULT_CHARSET));
		} catch (Exception e) {
			log.error("MD5 Sign Error.", e);
		}
	}
	
	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
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
	 * @param signature the signature to set
	 */
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
}