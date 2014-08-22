package tulip.web.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午8:43:08
 */
public class ServiceFactory {
	
	private static final Map<String, TulipService> SERVICES = new ConcurrentHashMap<String, TulipService>();

	public static void register(TulipService service) {
		SERVICES.put(service.getName(), service);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends TulipService> T getService(String name) {
		return (T) SERVICES.get(name);
	}
}