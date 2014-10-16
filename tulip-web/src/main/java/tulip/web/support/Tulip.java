package tulip.web.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月5日 下午4:03:32
 */
public class Tulip {
	
	public static final String DEFAULT_TEMPLATES_BASE_URI = "WEB-INF/templates";
	public static final String DEFAULT_TEMPLATES_SUFFIX = ".vm";
	
	public static final String DEFAULT_TEMPLATES_SCREEN_URI = "screen";
	public static final String DEFAULT_TEMPLATES_LAYOUT_URI = "layout";
	public static final String DEFAULT_TEMPLATES_DEFAULT_LAYOUT = "default";
	public static final String DEFAULT_TEMPLATES_SCREEN_KEY = "screen_placeholder";
	
	protected static final String VELOCITY_ENGINE_TAG = "velocity-engine";
	protected static final String VIEW_CONTROLLER_TAG = "view-controller";
	protected static final String EVENT_CARTRIDGE_TAG = "event-cartridge";
	protected static final String VIEW_RESOLVER_TAG = "view-resolver";
	protected static final String COMMONS_TAG = "commons";

	protected static final String CONTROLLER_URI_ATTR = "controller-uri";
	protected static final String CONTROLLER_NAME_ATTR = "name";
	protected static final String EVENT_HANDLER_TAG = "handler";
	protected static final String TEMPLATES_SCREEN_KEY_TAG = "screen-key";
	protected static final String TEMPLATES_DEFAULT_LAYOUT_TAG = "default-layout";
	protected static final String TEMPLATES_LAYOUT_URI_TAG = "layout-uri";
	protected static final String TEMPLATES_SCREEN_URI_TAG = "screen-uri";
	protected static final String TEMPLATES_SUFFIX_TAG = "suffix";
	protected static final String TEMPLATES_URI_TAG = "templates-uri";
	
	public Tulip() {
		super();
	}
	
	protected String templates_uri = DEFAULT_TEMPLATES_BASE_URI;
	protected String suffix = DEFAULT_TEMPLATES_SUFFIX;
	
	protected String screen_uri = DEFAULT_TEMPLATES_SCREEN_URI;
	protected String layout_uri = DEFAULT_TEMPLATES_LAYOUT_URI;
	protected String default_layout = DEFAULT_TEMPLATES_DEFAULT_LAYOUT;
	protected String screen_key = DEFAULT_TEMPLATES_SCREEN_KEY;
	
	protected List<Class<?>> handlers = new ArrayList<Class<?>>();
	
	protected Map<String, String> controllers = new HashMap<String, String>();
	
	protected String config_location;
	protected Class<?> velocity_engine_configurer;
	
}