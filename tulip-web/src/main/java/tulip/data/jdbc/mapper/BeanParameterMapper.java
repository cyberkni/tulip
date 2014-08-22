package tulip.data.jdbc.mapper;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月21日 上午11:09:28
 */
public class BeanParameterMapper extends AbstractSqlParameterSource {
	
	protected final Log log = LogFactory.getLog(getClass());

	private final BeanWrapper beanWrapper;
	
	private Map<String, PropertyDescriptor> mappedFields;
	
	private String[] propertyNames;

	public BeanParameterMapper(Object beanObject) {
		super();
		this.beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(beanObject);
		this.mappedFields = ClassMapper.mapper(beanObject.getClass());
	}
	
	@Override
	public boolean hasValue(String paramName) {
		PropertyDescriptor pd = mappedFields.get(paramName);
		if(pd == null) {
			return false;
		}
		return beanWrapper.isReadableProperty(pd.getName());
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		PropertyDescriptor pd = mappedFields.get(paramName);
		if(pd == null) {
			return null;
		}
		try {
			return beanWrapper.getPropertyValue(pd.getName());
		}
		catch (NotReadablePropertyException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	/**
	 * Provide access to the property names of the wrapped bean.
	 * Uses support provided in the {@link PropertyAccessor} interface.
	 * @return an array containing all the known property names
	 */
	public String[] getReadablePropertyNames() {
		if (propertyNames == null) {
			List<String> names = new ArrayList<String>();
			PropertyDescriptor[] props = beanWrapper.getPropertyDescriptors();
			for (PropertyDescriptor pd : props) {
				if (beanWrapper.isReadableProperty(pd.getName())) {
					names.add(pd.getName());
				}
			}
			propertyNames = names.toArray(new String[names.size()]);
		}
		return propertyNames;
	}

	/**
	 * Derives a default SQL type from the corresponding property type.
	 * @see org.springframework.jdbc.core.StatementCreatorUtils#javaTypeToSqlParameterType
	 */
	@Override
	public int getSqlType(String paramName) {
		int sqlType = super.getSqlType(paramName);
		if (sqlType != TYPE_UNKNOWN) {
			return sqlType;
		}
		PropertyDescriptor pd = mappedFields.get(paramName);
		if(pd == null) {
			return sqlType;
		}
		Class<?> propType = beanWrapper.getPropertyType(pd.getName());
		sqlType = StatementCreatorUtils.javaTypeToSqlParameterType(propType);
		registerSqlType(paramName, sqlType);
		return sqlType;
	}
	
	public static BeanParameterMapper newInstance(Object beanObject) {
		return new BeanParameterMapper(beanObject);
	}
	
	public static MapSqlParameterSource newMapParameterMapper(Map<String, ?> paramMap) {
		return new MapSqlParameterSource(paramMap);
	}
}