package tulip.web.module;

import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午4:53:01
 */
public abstract class BaseModule extends WebApplicationObjectSupport implements Module {

	protected String name;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public final void setBeanName(String name) {
		this.name = name;
		Module.MODULES.put(name, this);
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			initialize();
		} catch (Exception e) {
			logger.error("Module Named : " + name + " Initialize Error.", e);
		}
	}

	public void initialize() throws Exception {
		
	}
}