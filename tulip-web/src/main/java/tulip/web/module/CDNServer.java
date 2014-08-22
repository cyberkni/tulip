package tulip.web.module;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午5:50:06
 */
public class CDNServer extends BaseModule {

	protected String host;
	protected int port;
	protected String context;
	protected String name;

	@Override
	public String render() {
		return "http://" + host + ":" + port + "/" + context + "/" + name;
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