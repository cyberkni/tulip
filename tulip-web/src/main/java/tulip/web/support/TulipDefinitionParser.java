package tulip.web.support;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		NodeList commonsList = element.getElementsByTagName(COMMONS_TAG);
		int len = commonsList.getLength();
		if (len > 0) {
			Element commons = (Element) commonsList.item(0);
			templates_uri = parseItem(commons, TEMPLATES_URI_TAG, DEFAULT_TEMPLATES_BASE_URI);
			suffix = parseItem(commons, TEMPLATES_SUFFIX_TAG, DEFAULT_TEMPLATES_SUFFIX);
		}
		NodeList viewResolverList = element.getElementsByTagName(VIEW_RESOLVER_TAG);
		len = viewResolverList.getLength();
		if (len > 0) {
			Element viewResolver = (Element) commonsList.item(0);
			screen_uri = parseItem(viewResolver, TEMPLATES_SCREEN_URI_TAG, DEFAULT_TEMPLATES_SCREEN_URI);
			layout_uri = parseItem(viewResolver, TEMPLATES_LAYOUT_URI_TAG, DEFAULT_TEMPLATES_LAYOUT_URI);
			default_layout = parseItem(viewResolver, TEMPLATES_DEFAULT_LAYOUT_TAG, DEFAULT_TEMPLATES_DEFAULT_LAYOUT);
			screen_key = parseItem(viewResolver, TEMPLATES_SCREEN_KEY_TAG, DEFAULT_TEMPLATES_SCREEN_KEY);
		}
		NodeList eventCartridgeList = element.getElementsByTagName(EVENT_CARTRIDGE_TAG);
		len = eventCartridgeList.getLength();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				String handler = parseItem((Element) eventCartridgeList.item(i), EVENT_HANDLER_TAG, "");
				if(StringUtils.isBlank(handler)) {
					continue;
				}
				try {
					handlers.add(ClassUtils.getDefaultClassLoader().loadClass(handler));
				} catch (Exception e) {
					log.error("Load Handler Class Named : " + handler + " Error.", e);
				}
			}
		}
		NodeList viewControllerList = element.getElementsByTagName(VIEW_CONTROLLER_TAG);
		len = viewControllerList.getLength();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				Element controller = (Element) viewControllerList.item(i);
				String name = controller.getAttribute(CONTROLLER_NAME_ATTR);
				String controller_uri = controller.getAttribute(CONTROLLER_URI_ATTR);
				controllers.put(name, controller_uri);
			}
		}
		NodeList velocityEngineList = element.getElementsByTagName(VELOCITY_ENGINE_TAG);
		len = velocityEngineList.getLength();
		if (len > 0) {
			Element velocityEngine = (Element) velocityEngineList.item(0);
			config_location = velocityEngine.getAttribute("config-location");
			String velocity_configurer = parseItem(element, "velocity-configurer", "", "class");
			if(StringUtils.isNotBlank(velocity_configurer)) {
				try {
					velocity_engine_configurer = ClassUtils.getDefaultClassLoader().loadClass(velocity_configurer);
				} catch (Exception e) {
					log.error("Load VelocityEngine Configurer Class Named :" + velocity_configurer + " Error.", e);
				}
			}
			
		}
	}

	private String parseItem(Element element, String itemName, String defaultValue) {
		return parseItem(element, itemName, defaultValue, "value");
	}

	private String parseItem(Element element, String itemName, String defaultValue, String key) {
		NodeList itemsList = element.getElementsByTagName(itemName);
		if (itemsList.getLength() <= 0) {
			return defaultValue;
		}
		Element item = (Element) itemsList.item(0);
		return StringUtils.defaultIfBlank(item.getAttribute(key), defaultValue);
	}
}