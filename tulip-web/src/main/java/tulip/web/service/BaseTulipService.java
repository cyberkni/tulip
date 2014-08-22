package tulip.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午8:38:15
 */
public abstract class BaseTulipService implements TulipService {

	protected final Log log = LogFactory.getLog(getClass());

	protected final String name;

	protected BaseTulipService(String name) {
		this.name = name;
		ServiceFactory.register(this);
	}

	protected void initialize() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			initialize();
		} catch (Exception e) {
			log.error("TulipService Named " + name + " Initialize Error.", e);
		}
	}

}