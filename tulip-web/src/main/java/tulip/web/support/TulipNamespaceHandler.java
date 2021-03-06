package tulip.web.support;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月28日 下午4:17:36
 */
public class TulipNamespaceHandler extends NamespaceHandlerSupport {

	//http://www.itjiehun.com/schema/tulip/service http://www.itjiehun.com/schema/tulip/tulip-1.0.xsd
	@Override
	public void init() {
		registerBeanDefinitionParser("tulip", new TulipDefinitionParser());
	}
}
