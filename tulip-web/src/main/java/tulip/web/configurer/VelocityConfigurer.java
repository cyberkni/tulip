package tulip.web.configurer;

import java.io.IOException;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.ResourceCacheImpl;
import org.apache.velocity.runtime.resource.ResourceManagerImpl;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.apache.velocity.runtime.resource.loader.JarResourceLoader;
import org.apache.velocity.runtime.resource.loader.URLResourceLoader;
import org.apache.velocity.tools.view.WebappResourceLoader;

/**
 * Velocity模板引擎配置。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午6:56:37
 */
public class VelocityConfigurer extends org.springframework.web.servlet.view.velocity.VelocityConfigurer {
	public static final String JAR_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL = "jar.resource.loader.modificationCheckInterval";
	public static final String JAR_RESOURCE_LOADER_CACHE = "jar.resource.loader.cache";
	public static final String JAR_RESOURCE_LOADER_CLASS = "jar.resource.loader.class";
	public static final String JAR_RESOURCE_LOADER = "jar";
	
	public static final String URL_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL = "url.resource.loader.modificationCheckInterval";
	public static final String URL_RESOURCE_LOADER_CACHE = "url.resource.loader.cache";
	public static final String URL_RESOURCE_LOADER_CLASS = "url.resource.loader.class";
	public static final String URL_RESOURCE_LOADER = "url";
	
	public static final String CLASS_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL = "class.resource.loader.modificationCheckInterval";
	public static final String CLASS_RESOURCE_LOADER_CACHE = "class.resource.loader.cache";
	public static final String CLASS_RESOURCE_LOADER_CLASS = "class.resource.loader.class";
	public static final String CLASS_RESOURCE_LOADER = "class";
	
	public static final String FILE_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL = "file.resource.loader.modificationCheckInterval";
	public static final String FILE_RESOURCE_LOADER_CACHE = "file.resource.loader.cache";
	public static final String FILE_RESOURCE_LOADER_CLASS = "file.resource.loader.class";
	public static final String FILE_RESOURCE_LOADER = "file";
	
	public static final String WEBAPP_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL = "webapp.resource.loader.modificationCheckInterval";
	public static final String WEBAPP_RESOURCE_LOADER_CACHE = "webapp.resource.loader.cache";
	public static final String WEBAPP_RESOURCE_LOADER_CLASS = "webapp.resource.loader.class";
	public static final String WEBAPP_RESOURCE_LOADER = "webapp";
	
	public static final boolean DEFAULT_FILE_RESOURCE_LOADER_CACHE = true;
	private static final int DEFAULT_RESOURCE_MANAGER_CACHE_SIZE = 2048;
	public static final String RESOURCE_MANAGER_CACHE_SIZE = "resource.manager.cache.size";
	public static final String DEFAULT_VM_PERM_INLINE_LOCAL = "true";
	public static final String DEFAULT_COUNTER_INITIAL_VALUE = "1";
	public static final String DEFAULT_INPUT_ENCODING = "UTF-8";
	public static final String DEFAULT_OUTPUT_ENCODING = "UTF-8";
	public static final String DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL = "3";
	public static final String DEFAULT_TEMPLATE_CACHE_ON = DEFAULT_VM_PERM_INLINE_LOCAL;
	public static final String DEFAULT_PARSER_POOL_SIZE = "100";
	public static final String DEFAULT_UBERSPECT_CLASSNAME = Uberspect.class.getName();

	/** inputEncoding */
	protected String inputEncoding = null;
	/** outputEncoding */
	protected String outputEncoding = null;
	
	public static void main(String[] args) {
		ExtendedProperties overridingProperties = new ExtendedProperties();
		overridingProperties.addProperty(RuntimeConstants.RESOURCE_LOADER, WEBAPP_RESOURCE_LOADER);
		overridingProperties.addProperty(RuntimeConstants.RESOURCE_LOADER, FILE_RESOURCE_LOADER);
		System.out.println(overridingProperties.getProperty(RuntimeConstants.RESOURCE_LOADER));
	}

	@Override
	public void afterPropertiesSet() throws IOException, VelocityException {
		setOverrideLogging(true);
		if (StringUtils.isEmpty(inputEncoding)) {
			inputEncoding = DEFAULT_INPUT_ENCODING;
		}
		if (StringUtils.isEmpty(outputEncoding)) {
			outputEncoding = DEFAULT_OUTPUT_ENCODING;
		}
		super.afterPropertiesSet();
	}

	@Override
	protected void postProcessVelocityEngine(VelocityEngine velocityEngine) {
		super.postProcessVelocityEngine(velocityEngine);

		velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, WEBAPP_RESOURCE_LOADER);
		velocityEngine.setProperty(WEBAPP_RESOURCE_LOADER_CLASS, WebappResourceLoader.class.getName());
		velocityEngine.setProperty(WEBAPP_RESOURCE_LOADER_CACHE, DEFAULT_TEMPLATE_CACHE_ON);
		velocityEngine.setProperty(WEBAPP_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL,
				DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL);

		velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, FILE_RESOURCE_LOADER);
		velocityEngine.setProperty(FILE_RESOURCE_LOADER_CLASS, FileResourceLoader.class.getName());
		velocityEngine.setProperty(FILE_RESOURCE_LOADER_CACHE, DEFAULT_TEMPLATE_CACHE_ON);
		velocityEngine.setProperty(FILE_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL,
				DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL);

		velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, CLASS_RESOURCE_LOADER);
		velocityEngine.setProperty(CLASS_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
		velocityEngine.setProperty(CLASS_RESOURCE_LOADER_CACHE, DEFAULT_TEMPLATE_CACHE_ON);
		velocityEngine.setProperty(CLASS_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL,
				DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL);

		velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, URL_RESOURCE_LOADER);
		velocityEngine.setProperty(URL_RESOURCE_LOADER_CLASS, URLResourceLoader.class.getName());
		velocityEngine.setProperty(URL_RESOURCE_LOADER_CACHE, DEFAULT_TEMPLATE_CACHE_ON);
		velocityEngine.setProperty(URL_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL,
				DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL);

		velocityEngine.addProperty(RuntimeConstants.RESOURCE_LOADER, JAR_RESOURCE_LOADER);
		velocityEngine.setProperty(JAR_RESOURCE_LOADER_CLASS, JarResourceLoader.class.getName());
		velocityEngine.setProperty(JAR_RESOURCE_LOADER_CACHE, DEFAULT_TEMPLATE_CACHE_ON);
		velocityEngine.setProperty(JAR_RESOURCE_LOADER_MODIFICATION_CHECK_INTERVAL,
				DEFAULT_TEMPLATE_MODIFICATION_CHECK_INTERVAL);

		velocityEngine.setProperty(VelocityEngine.RESOURCE_MANAGER_LOGWHENFOUND, String.valueOf(true));
		velocityEngine.setProperty(VelocityEngine.PARSER_POOL_SIZE, DEFAULT_PARSER_POOL_SIZE);
		velocityEngine.setProperty(VelocityEngine.UBERSPECT_CLASSNAME, DEFAULT_UBERSPECT_CLASSNAME);
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_MANAGER_CACHE_CLASS, ResourceCacheImpl.class.getName());
		velocityEngine.setProperty(RESOURCE_MANAGER_CACHE_SIZE, DEFAULT_RESOURCE_MANAGER_CACHE_SIZE);
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_MANAGER_CLASS, ResourceManagerImpl.class.getName());
		velocityEngine.setProperty(RuntimeConstants.COUNTER_INITIAL_VALUE, DEFAULT_COUNTER_INITIAL_VALUE);
		velocityEngine.setProperty(RuntimeConstants.INPUT_ENCODING, inputEncoding);
		velocityEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, outputEncoding);
		velocityEngine.setProperty(RuntimeConstants.VM_PERM_INLINE_LOCAL, DEFAULT_VM_PERM_INLINE_LOCAL);
		// org.apache.velocity.runtime.log.AvalonLogChute
		// org.apache.velocity.runtime.log.Log4JLogChute
		// org.apache.velocity.runtime.log.CommonsLogLogChute
		// org.apache.velocity.runtime.log.ServletLogChute
		// org.apache.velocity.runtime.log.JdkLogChute
		velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new Log4JLogChute());
		velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, DEFAULT_FILE_RESOURCE_LOADER_CACHE);
	}

	public void setInputEncoding(String inputEncoding) {
		this.inputEncoding = inputEncoding;
	}

	public void setOutputEncoding(String outputEncoding) {
		this.outputEncoding = outputEncoding;
	}
}