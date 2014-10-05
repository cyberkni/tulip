package tulip.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月5日 下午3:54:46
 */
public class ConvertUtils {
	
	private static final Log log = LogFactory.getLog(ConvertUtils.class);
	
	private static final ConversionService conversion = new DefaultConversionService();
	
	public static <T> T convert(Object source, Class<T> targetType, T defaultValue) {
		if(source == null) {
			return defaultValue;
		}
		try {
			return conversion.convert(source, targetType);
		} catch (Exception e) {
			log.error("convert Error.", e);
		}
		return defaultValue;
	}
}