package tulip.data.jdbc.mapper;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月20日 下午6:29:29
 */
public class BeanRowMapper<T> implements RowMapper<T> {
	protected final Log log = LogFactory.getLog(getClass());

	/** The class we are mapping to */
	private Class<T> mappedClass;
	/** Map of the fields we provide mapping for */
	private Map<String, PropertyDescriptor> mappedFields;

	public BeanRowMapper() {
		super();
	}

	public BeanRowMapper(Class<T> mappedClass) {
		initialize(mappedClass);
	}

	public void setMappedClass(Class<T> mappedClass) {
		if (this.mappedClass != null) {
			return;
		}
		initialize(mappedClass);
	}
	
	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = ClassMapper.mapper(mappedClass);
	}

	@Override
	public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Assert.state(this.mappedClass != null, "Mapped class was not specified");
		T mappedObject = BeanUtils.instantiate(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		initBeanWrapper(bw);

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			PropertyDescriptor pd = this.mappedFields.get(column.toLowerCase());
			if (pd == null) {
				continue;
			}
			try {
				Object value = getColumnValue(rs, index, pd);
				if (log.isDebugEnabled() && rowNumber == 0) {
					log.debug("Mapping column '" + column + "' to property '" + pd.getName() + "' of type "
							+ pd.getPropertyType());
				}
				try {
					bw.setPropertyValue(pd.getName(), value);
				} catch (TypeMismatchException e) {
					log.error("Intercepted TypeMismatchException for row " + rowNumber + " and column '" + column
							+ "' with value " + value + " when setting property '" + pd.getName() + "' of type "
							+ pd.getPropertyType() + " on object: " + mappedObject);
				}
			} catch (NotWritablePropertyException ex) {
				throw new DataRetrievalFailureException("Unable to map column " + column + " to property "
						+ pd.getName(), ex);
			}
		}
		return mappedObject;
	}

	/**
	 * Initialize the given BeanWrapper to be used for row mapping. To be called
	 * for each row.
	 * <p>
	 * The default implementation is empty. Can be overridden in subclasses.
	 * 
	 * @param bw
	 *            the BeanWrapper to initialize
	 */
	protected void initBeanWrapper(BeanWrapper bw) {
	}

	/**
	 * Retrieve a JDBC object value for the specified column.
	 * <p>
	 * The default implementation calls
	 * {@link JdbcUtils#getResultSetValue(java.sql.ResultSet, int, Class)}.
	 * Subclasses may override this to check specific value types upfront, or to
	 * post-process values return from {@code getResultSetValue}.
	 * 
	 * @param rs
	 *            is the ResultSet holding the data
	 * @param index
	 *            is the column index
	 * @param pd
	 *            the bean property that each result object is expected to match
	 *            (or {@code null} if none specified)
	 * @return the Object value
	 * @throws SQLException
	 *             in case of extraction failure
	 * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(java.sql.ResultSet,
	 *      int, Class)
	 */
	protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
	}

	/**
	 * Static factory method to create a new BeanPropertyRowMapper (with the
	 * mapped class specified only once).
	 * 
	 * @param mappedClass
	 *            the class that each row should be mapped to
	 */
	public static <T> BeanRowMapper<T> newInstance(Class<T> mappedClass) {
		return new BeanRowMapper<T>(mappedClass);
	}
	
	public static <T> ResultSetExtractor<T> newSingleExtractor(Class<T> mappedClass) {
		final BeanRowMapper<T> beanMapper = BeanRowMapper.newInstance(mappedClass);
		return new ResultSetExtractor<T>() {
			@Override
			public T extractData(ResultSet rs) throws SQLException, DataAccessException {
				return beanMapper.mapRow(rs, 0);
			}
		};
	}
	
	public static <T> RowMapperResultSetExtractor<T> newMultiExtractor(final Class<T> mappedClass) {
		return new RowMapperResultSetExtractor<T>(BeanRowMapper.newInstance(mappedClass));
	}
}