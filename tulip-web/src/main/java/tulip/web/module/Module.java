package tulip.web.module;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import tulip.web.Renderable;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午4:50:39
 */
public interface Module extends Renderable, BeanNameAware, InitializingBean {
	Map<String, Module> MODULES = new HashMap<String, Module>();
	String getName();
}