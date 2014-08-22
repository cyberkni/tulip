package tulip.web.module;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.context.Context;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import tulip.util.CollectionUtil;
import tulip.util.StringUtil;
import tulip.web.configurer.EventCartridgeConfigurer;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月7日 下午4:58:56
 */
public class Controller extends BaseModule {

	protected VelocityEngine velocityEngine;
	protected EventCartridge eventCartridge;

	protected String target;

	protected Map<String, Object> model;
	
	protected String context;
	/** 模板的扩展名称 */
	protected String suffix;

	@Override
	public String render() {
		String templateURI = templateURI();
		try {
			return render(createVelocityContext(createMergedOutputModel()), getTemplate(templateURI));
		} catch (Exception e) {
			logger.error(String.format("Try Loading And Rending Controller Module Named [%s] Error.", templateURI), e);
		}
		return StringUtil.EMPTY;
	}
	
	protected Context createVelocityContext(Map<String, Object> model) {
		VelocityContext context = new VelocityContext(model);
		if (eventCartridge == null) {
			return context;
		}
		eventCartridge.attachToContext(context);
		return context;
	}
	
	protected String templateURI() {
		return File.separator + context + File.separator + target + suffix;
	}

	protected String render(Context context, Template template) {
		if (template == null || context == null) {
			return StringUtil.EMPTY;
		}
		try {
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			return sw.toString();
		} catch (Exception e) {
			logger.error(String.format("Controller Module Named [%s] Render Error.", template.getName()), e);
		}
		return StringUtil.EMPTY;
	}

	protected Template getTemplate(String name) throws Exception {
		return velocityEngine.getTemplate(name);
	}
	
	public Controller with(String key, Object val) {
		if(StringUtil.isBlank(key) || val == null) {
			return this;
		}
		if(model == null) {
			model = new HashMap<String, Object>();
		}
		model.put(key, val);
		return this;
	}
	
	protected Map<String, Object> createMergedOutputModel() {
		Map<String, Object> mergedModel = new LinkedHashMap<String, Object>();
		if(!CollectionUtil.isEmpty(Module.MODULES)) {
			mergedModel.putAll(Module.MODULES);
		}
		if(!CollectionUtil.isEmpty(model)) {
			mergedModel.putAll(model);
		}
		return mergedModel;
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		if(velocityEngine == null) {
			velocityEngine = autodetectVelocityEngine();
		}
		if(eventCartridge == null) {
			eventCartridge = autodetectEventCartridge();
		}
	}
	
	protected EventCartridge autodetectEventCartridge() {
		try {
			EventCartridgeConfigurer eventCartridgeConfigurer = BeanFactoryUtils.beanOfTypeIncludingAncestors(
					getApplicationContext(), EventCartridgeConfigurer.class, true, false);
			return eventCartridgeConfigurer.getEventCartridge();
		} catch (NoSuchBeanDefinitionException ex) {
			logger.warn("No EventCartridgeConfigurer bean in this Application Context.", ex);
		} catch (Exception e) {
			logger.error("autodetectEventCartridge error.", e);
		}
		return null;
	}

	protected VelocityEngine autodetectVelocityEngine() throws BeansException {
		try {
			VelocityConfig velocityConfig = BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(),
					VelocityConfig.class, true, false);
			return velocityConfig.getVelocityEngine();
		} catch (NoSuchBeanDefinitionException ex) {
			throw new ApplicationContextException(
					"Must define a single VelocityConfig bean in this web application context "
							+ "(may be inherited): VelocityConfigurer is the usual implementation. "
							+ "This bean may be given any name.", ex);
		}
	}

	public Controller target(String target) {
		this.target = target;
		return this;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}