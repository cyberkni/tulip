package tulip.web.support;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月28日 下午4:20:30
 */
public class TulipDefinitionParser implements BeanDefinitionParser {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/tulip-1.0.xml");
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		AutowireCapableBeanFactory autowireCapableBeanFactory = (AutowireCapableBeanFactory) parserContext.getRegistry();
		System.out.println(autowireCapableBeanFactory);
		return null;
	}
}