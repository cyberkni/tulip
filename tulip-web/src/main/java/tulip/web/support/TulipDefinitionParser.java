package tulip.web.support;

import java.util.HashMap;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tulip.util.ConvertUtils;
import tulip.util.StringUtil;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月28日 下午4:20:30
 */
public class TulipDefinitionParser extends Tulip implements BeanDefinitionParser {

	private final Log log = LogFactory.getLog(getClass());

	public static void main0(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/tulip-1.0.xml");
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		parseConfiguration(element);
		
		
		
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) parserContext.getRegistry();

		return null;
	}
	
	private void parseConfiguration(Element element) {
		Configuration configuration = new MapConfiguration(new HashMap<String, Object>());
		auto_config = ConvertUtils.convert(element.getAttribute("auto-config"), boolean.class, true);
		NodeList commonsList = element.getElementsByTagName(COMMONS_TAG);
		int len = commonsList.getLength();
		if(len > 0) {
			Element commons = (Element) commonsList.item(0);
			templates_uri = tulipItem(commons, "templates-uri", "WEB-INF/templates");
			suffix = tulipItem(commons, "suffix", ".vm");
		}
		NodeList viewResolverList = element.getElementsByTagName(VIEW_RESOLVER_TAG);
		len = viewResolverList.getLength();
		if(len > 0) {
			Element viewResolver = (Element) commonsList.item(0);
			configuration.setProperty("tulip:screen-uri", tulipItem(viewResolver, "screen-uri", "screen"));
			configuration.setProperty("tulip:layout-uri", tulipItem(viewResolver, "layout-uri", "layout"));
			configuration.setProperty("tulip:default-layout", tulipItem(viewResolver, "default-layout", "default"));
			configuration.setProperty("tulip:screen-key", tulipItem(viewResolver, "screen-key", "screen_placeholder"));
		}
		NodeList eventCartridgeList = element.getElementsByTagName(EVENT_CARTRIDGE_TAG);
		len = eventCartridgeList.getLength();
		if(len > 0) {
			for(int i = 0; i< len; i++) {
				configuration.addProperty("tulip:handlers", tulipItem((Element) eventCartridgeList.item(i), "handler", ""));
			}
		}
		NodeList viewControllerList = element.getElementsByTagName(VIEW_CONTROLLER_TAG);
		len = viewControllerList.getLength();
		if(len > 0) {
			for(int i = 0; i< len; i++) {
				Element controller = (Element) viewControllerList.item(i);
				String name = controller.getAttribute("name");
				String controller_uri = controller.getAttribute("controller-uri");
				Entry entry = new Entry(name, controller_uri);
				configuration.addProperty("tulip:controllers", entry);
			}
		}
		NodeList velocityEngineList = element.getElementsByTagName(VELOCITY_ENGINE_TAG);
		len = velocityEngineList.getLength();
		if(len > 0) {
			Element velocityEngine = (Element) velocityEngineList.item(0);
			configuration.setProperty("tulip:config-location", velocityEngine.getAttribute("config-location"));
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			configuration.setProperty(key, value);
			
		}
	}
	
	private String tulipItem(Element element, String itemName, String defaultValue) {
		return tulipItem(element, itemName, defaultValue, "value");
	}
	
	private String tulipItem(Element element, String itemName, String defaultValue, String key) {
		NodeList itemsList = element.getElementsByTagName(itemName);
		if(itemsList.getLength() <= 0) {
			return defaultValue;
		}
		Element item = (Element) itemsList.item(0);
		return StringUtil.defaultIfBlank(item.getAttribute(key), defaultValue);
	}
	
	class Entry {
		
		final String name;
		final String controller_uri;
		
		public Entry(String name, String controller_uri) {
			super();
			this.name = name;
			this.controller_uri = controller_uri;
		}
	}
}