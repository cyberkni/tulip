package tulip.data.jdbc.named;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.Assert;

import tulip.util.CollectionUtil;

/**
 * Template class with a basic set of JDBC operations, allowing the use
 * of named parameters rather than traditional '?' placeholders.
 *
 * <p>This class delegates to a wrapped {@link #getJdbcOperations() JdbcTemplate}
 * once the substitution from named parameters to JDBC style '?' placeholders is
 * done at execution time. It also allows for expanding a {@link java.util.List}
 * of values to the appropriate number of placeholders.
 *
 * <p>The underlying {@link org.springframework.jdbc.core.JdbcTemplate} is
 * exposed to allow for convenient access to the traditional
 * {@link org.springframework.jdbc.core.JdbcTemplate} methods.
 *
 * <p><b>NOTE: An instance of this class is thread-safe once configured.</b>
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月21日 下午6:33:46
 */
public class NamedJdbcTemplate implements NamedJdbcOperations {

	/** Default maximum number of entries for this template's SQL cache: 256 */
	public static final int DEFAULT_CACHE_LIMIT = 256;

	/** The JdbcTemplate we are wrapping */
	private JdbcOperations classicJdbcTemplate;

	private volatile int cacheLimit = DEFAULT_CACHE_LIMIT;

	/** Cache of original SQL String to ParsedSql representation */
	private final Map<String, ParsedSql> parsedSqlCache = new LinkedHashMap<String, ParsedSql>(DEFAULT_CACHE_LIMIT, 0.75f, true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Map.Entry<String, ParsedSql> eldest) {
			return size() > getCacheLimit();
		}
	};

	public NamedJdbcTemplate() {
		super();
	}

	/**
	 * Create a new NamedParameterJdbcTemplate for the given {@link DataSource}.
	 * <p>
	 * Creates a classic Spring
	 * {@link org.springframework.jdbc.core.JdbcTemplate} and wraps it.
	 * 
	 * @param dataSource
	 *            the JDBC DataSource to access
	 */
	public NamedJdbcTemplate(DataSource dataSource) {
		Assert.notNull(dataSource, "DataSource must not be null");
		initialize(dataSource);
	}

	/**
	 * Create a new NamedParameterJdbcTemplate for the given classic Spring
	 * {@link org.springframework.jdbc.core.JdbcTemplate}.
	 * 
	 * @param classicJdbcTemplate
	 *            the classic Spring JdbcTemplate to wrap
	 */
	public NamedJdbcTemplate(JdbcOperations classicJdbcTemplate) {
		Assert.notNull(classicJdbcTemplate, "JdbcTemplate must not be null");
		initialize(classicJdbcTemplate);
	}

	public void setJdbcTemplate(JdbcOperations jdbcTemplate) {
		initialize(jdbcTemplate);
	}

	public void setDataSource(DataSource dataSource) {
		initialize(dataSource);
	}

	protected void initialize(DataSource dataSource) {
		if (classicJdbcTemplate != null) {
			return;
		}
		classicJdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected void initialize(JdbcOperations jdbcTemplate) {
		if (classicJdbcTemplate != null) {
			return;
		}
		classicJdbcTemplate = jdbcTemplate;
	}

	/**
	 * Expose the classic Spring JdbcTemplate to allow invocation of less
	 * commonly used methods.
	 */
	@Override
	public JdbcOperations getJdbcOperations() {
		return this.classicJdbcTemplate;
	}

	/**
	 * Specify the maximum number of entries for this template's SQL cache.
	 * Default is 256.
	 */
	public void setCacheLimit(int cacheLimit) {
		this.cacheLimit = cacheLimit;
	}

	/**
	 * Return the maximum number of entries for this template's SQL cache.
	 */
	public int getCacheLimit() {
		return this.cacheLimit;
	}

	@Override
	public <T> T execute(String sql, SqlParameterSource paramSource, PreparedStatementCallback<T> action)
			throws DataAccessException {

		return getJdbcOperations().execute(getPreparedStatementCreator(sql, paramSource), action);
	}

	@Override
	public <T> T execute(String sql, Map<String, ?> paramMap, PreparedStatementCallback<T> action)
			throws DataAccessException {

		return execute(sql, new MapSqlParameterSource(paramMap), action);
	}

	@Override
	public <T> T execute(String sql, PreparedStatementCallback<T> action) throws DataAccessException {
		return execute(sql, EmptySqlParameterSource.INSTANCE, action);
	}

	@Override
	public <T> T query(String sql, SqlParameterSource paramSource, ResultSetExtractor<T> rse)
			throws DataAccessException {

		return getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rse);
	}

	@Override
	public <T> T query(String sql, Map<String, ?> paramMap, ResultSetExtractor<T> rse) throws DataAccessException {

		return query(sql, new MapSqlParameterSource(paramMap), rse);
	}

	@Override
	public <T> T query(String sql, ResultSetExtractor<T> rse) throws DataAccessException {
		return query(sql, EmptySqlParameterSource.INSTANCE, rse);
	}

	@Override
	public void query(String sql, SqlParameterSource paramSource, RowCallbackHandler rch) throws DataAccessException {

		getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rch);
	}

	@Override
	public void query(String sql, Map<String, ?> paramMap, RowCallbackHandler rch) throws DataAccessException {

		query(sql, new MapSqlParameterSource(paramMap), rch);
	}

	@Override
	public void query(String sql, RowCallbackHandler rch) throws DataAccessException {
		query(sql, EmptySqlParameterSource.INSTANCE, rch);
	}

	@Override
	public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {

		return getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rowMapper);
	}

	@Override
	public <T> List<T> query(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException {

		return query(sql, new MapSqlParameterSource(paramMap), rowMapper);
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
		return query(sql, EmptySqlParameterSource.INSTANCE, rowMapper);
	}

	@Override
	public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {
		List<T> results = getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rowMapper);
		if(CollectionUtil.isEmpty(results)) {
			return null;
		}
		return DataAccessUtils.requiredSingleResult(results);
	}

	@Override
	public <T> T queryForObject(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException {
		return queryForObject(sql, new MapSqlParameterSource(paramMap), rowMapper);
	}

	@Override
	public <T> T queryForObject(String sql, SqlParameterSource paramSource, Class<T> requiredType)
			throws DataAccessException {
		return queryForObject(sql, paramSource, new SingleColumnRowMapper<T>(requiredType));
	}

	@Override
	public <T> T queryForObject(String sql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException {
		return queryForObject(sql, paramMap, new SingleColumnRowMapper<T>(requiredType));
	}

	@Override
	public Map<String, Object> queryForMap(String sql, SqlParameterSource paramSource) throws DataAccessException {
		return queryForObject(sql, paramSource, new ColumnMapRowMapper());
	}

	@Override
	public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return queryForObject(sql, paramMap, new ColumnMapRowMapper());
	}

	@Override
	public <T> List<T> queryForList(String sql, SqlParameterSource paramSource, Class<T> elementType)
			throws DataAccessException {

		return query(sql, paramSource, new SingleColumnRowMapper<T>(elementType));
	}

	@Override
	public <T> List<T> queryForList(String sql, Map<String, ?> paramMap, Class<T> elementType)
			throws DataAccessException {

		return queryForList(sql, new MapSqlParameterSource(paramMap), elementType);
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql, SqlParameterSource paramSource)
			throws DataAccessException {

		return query(sql, paramSource, new ColumnMapRowMapper());
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql, Map<String, ?> paramMap) throws DataAccessException {

		return queryForList(sql, new MapSqlParameterSource(paramMap));
	}

	@Override
	public SqlRowSet queryForRowSet(String sql, SqlParameterSource paramSource) throws DataAccessException {
		return getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource),
				new SqlRowSetResultSetExtractor());
	}

	@Override
	public SqlRowSet queryForRowSet(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return queryForRowSet(sql, new MapSqlParameterSource(paramMap));
	}

	@Override
	public int update(String sql, SqlParameterSource paramSource) throws DataAccessException {
		return getJdbcOperations().update(getPreparedStatementCreator(sql, paramSource));
	}

	@Override
	public int update(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return update(sql, new MapSqlParameterSource(paramMap));
	}

	@Override
	public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder)
			throws DataAccessException {

		return update(sql, paramSource, generatedKeyHolder, null);
	}

	@Override
	public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder, String[] keyColumnNames)
			throws DataAccessException {

		ParsedSql parsedSql = getParsedSql(sql);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
		List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
		if (keyColumnNames != null) {
			pscf.setGeneratedKeysColumnNames(keyColumnNames);
		} else {
			pscf.setReturnGeneratedKeys(true);
		}
		return getJdbcOperations().update(pscf.newPreparedStatementCreator(params), generatedKeyHolder);
	}

	@Override
	public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) {
		SqlParameterSource[] batchArgs = new SqlParameterSource[batchValues.length];
		int i = 0;
		for (Map<String, ?> values : batchValues) {
			batchArgs[i] = new MapSqlParameterSource(values);
			i++;
		}
		return batchUpdate(sql, batchArgs);
	}

	@Override
	public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
		ParsedSql parsedSql = this.getParsedSql(sql);
		return NamedParameterBatchUpdateUtils.executeBatchUpdateWithNamedParameters(parsedSql, batchArgs,
				getJdbcOperations());
	}

	/**
	 * Build a PreparedStatementCreator based on the given SQL and named
	 * parameters.
	 * <p>
	 * Note: Not used for the {@code update} variant with generated key
	 * handling.
	 * 
	 * @param sql
	 *            SQL to execute
	 * @param paramSource
	 *            container of arguments to bind
	 * @return the corresponding PreparedStatementCreator
	 */
	protected PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource) {
		ParsedSql parsedSql = getParsedSql(sql);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
		List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
		return pscf.newPreparedStatementCreator(params);
	}

	/**
	 * Obtain a parsed representation of the given SQL statement.
	 * <p>
	 * The default implementation uses an LRU cache with an upper limit of 256
	 * entries.
	 * 
	 * @param sql
	 *            the original SQL
	 * @return a representation of the parsed SQL statement
	 */
	protected ParsedSql getParsedSql(String sql) {
		if (getCacheLimit() <= 0) {
			return NamedParameterUtils.parseSqlStatement(sql);
		}
		synchronized (this.parsedSqlCache) {
			ParsedSql parsedSql = this.parsedSqlCache.get(sql);
			if (parsedSql == null) {
				parsedSql = NamedParameterUtils.parseSqlStatement(sql);
				this.parsedSqlCache.put(sql, parsedSql);
			}
			return parsedSql;
		}
	}
}
