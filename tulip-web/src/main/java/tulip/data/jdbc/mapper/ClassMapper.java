package tulip.data.jdbc.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月21日 上午11:20:02
 */
public class ClassMapper {

	public static <T> Map<String, PropertyDescriptor> mapper(Class<T> mappedClass) {
		Map<String, PropertyDescriptor> mappedFields = new HashMap<String, PropertyDescriptor>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		if (pds == null || pds.length <= 0) {
			return mappedFields;
		}
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() == null) {
				continue;
			}
			Field field = ReflectionUtils.findField(mappedClass, pd.getName(), pd.getPropertyType());
			if (field == null) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				continue;
			}
			mappedFields.put(column.name().toLowerCase(), pd);
		}
		return mappedFields;
	}
}