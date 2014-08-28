package tulip.web.support;

import java.util.HashMap;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;


/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月28日 下午4:42:05
 */
public class TulipConfigurer {
	
	public static final String NAME = "tulipConfigurer";
	
	private static DefaultListableBeanFactory beanFactory;

	private static final Configuration configuration = new MapConfiguration(new HashMap<String, Object>());
	
	static {
		configuration.setProperty("", "");
	}
	
	public final TulipConfigurer setConfigurer(String key, Object value) {
		configuration.setProperty(key, value);
		beanFactory.initializeBean(this, NAME);
		return this;
	}
	
	public static void initializeBean() {
		
	}
	
	public static void setBeanFactory(DefaultListableBeanFactory beanFactory) {
		TulipConfigurer.beanFactory = beanFactory;
	}
}