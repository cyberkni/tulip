package tulip.web.support;

import java.util.HashMap;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月28日 下午4:42:05
 */
public class TulipConfigurer extends WebApplicationObjectSupport {
	
	public static final String NAME = "tulipConfigurer";
	
	private final Configuration configuration = new MapConfiguration(new HashMap<String, Object>());
	
	public static void main(String[] args) {
		TulipConfigurer configurer = new TulipConfigurer();
		configurer.setConfigurer("name", "fei.liu");
		configurer.addConfigurer("team", "fei.liu");
		configurer.addConfigurer("team", "jianzheng.wang");
		configurer.addConfigurer("team", "zhaohui.su");
		configurer.configuration.addProperty("team", configurer);
		System.out.println(configurer.configuration.getString("name"));
		System.out.println(configurer.configuration.getList("team"));
	}
	
	public final TulipConfigurer setConfigurer(String key, Object value) {
		configuration.setProperty(key, value);
		return this;
	}

	public final TulipConfigurer addConfigurer(String key, Object value) {
		configuration.addProperty(key, value);
		return this;
	}
}