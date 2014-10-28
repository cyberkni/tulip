package tulip.web.module;

import tulip.util.StringUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午5:50:06
 */
public class CDNServer extends BaseModule {

	public static final String HTTP_PROTOCOL = "http";
	public static final int PORT_80 = 80;
	
	protected String protocol = HTTP_PROTOCOL;
	
	protected String host = StringUtil.EMPTY;
	protected int port = PORT_80;
	protected String context = StringUtil.EMPTY;
	protected String name;
	
	private String profile;
	
	public String getProfile() {
		if(StringUtil.isBlank(profile)) {
			profile = new StringBuffer()
			.append(protocol).append("://")
			.append(host).append((port == PORT_80 ? StringUtil.EMPTY : port))
			.append("/").append((StringUtil.isBlank(context) ? StringUtil.EMPTY : (context + "/")))
			.toString();
		}
		return profile;
	}

	@Override
	public String render() {
		return getProfile() + name;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public CDNServer name(String name) {
		this.name = name;
		return this;
	}
}