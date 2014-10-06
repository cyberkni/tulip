package tulip.web.support;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月5日 下午4:03:32
 */
public class Tulip {
	
	protected static final String VELOCITY_ENGINE_TAG = "velocity-engine";
	protected static final String VIEW_CONTROLLER_TAG = "view-controller";
	protected static final String EVENT_CARTRIDGE_TAG = "event-cartridge";
	protected static final String VIEW_RESOLVER_TAG = "view-resolver";
	protected static final String COMMONS_TAG = "commons";
	
	public Tulip() {
		super();
	}
	
	protected boolean auto_config;
	protected String templates_uri;
	protected String suffix;
}