package tulip.web.view;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.util.NestedServletException;

import tulip.util.StringUtil;
import tulip.web.configurer.EventCartridgeConfigurer;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午5:00:57
 */
public class TulipView extends AbstractView {

	/** 模板文件的根目录 */
	protected String templates;
	/** Screen模板文件目录 */
	protected String screen;
	/** Layout模板文件目录 */
	protected String layout;

	/** Velocity模板默认加载的Layout模板名称 */
	protected String defaultLayout;
	/** Screen模板解析之后填充到Layout的key键 */
	protected String screenKey;

	/** 本次请求对应的视图名称 */
	protected String viewName;
	/** 模板的扩展名称 */
	protected String suffix;
	
	protected VelocityEngine velocityEngine;
	protected EventCartridge eventCartridge;
	
	/**
	 * 合并上下文并执行模板的解析和渲染
	 * 
	 * @see #render(Map, HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Context context = createVelocityContext(model, request, response);
		doRender(context, response);
	}

	/**
	 * 执行模板的解析和渲染，自动选择同名的Layout模版，同名的模版不存在的时候使用系统默认的统一Layout模板。
	 * 
	 * @see #render(Map, HttpServletRequest, HttpServletResponse)
	 * @see #renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse)
	 */
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		Template screen = getTemplate(screenURI());
		Template layout = checkAndGetTemplate(layoutURI());
		if (layout == null) {
			layout = checkAndGetTemplate(defaultLayoutURI());
		}
		if (layout == null) {
			mergeTemplate(screen, context, response);
			return;
		}
		context.put(screenKey, templateRender(context, screen));
		mergeTemplate(layout, context, response);
	}

	/**
	 * 模板解析渲染，并返回生成的结果。
	 */
	protected String templateRender(Context context, Template template) {
		if (template == null || context == null) {
			return StringUtil.EMPTY;
		}
		try {
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			return sw.toString();
		} catch (Exception e) {
			logger.error(String.format("View Named [%s] Render Error.", template.getName()), e);
		}
		return StringUtil.EMPTY;
	}

	/**
	 * 将模板解析渲染后的结果响应到客户端。
	 */
	protected void mergeTemplate(Template template, Context context, HttpServletResponse response) throws Exception {
		try {
			template.merge(context, response.getWriter());
		} catch (MethodInvocationException ex) {
			Throwable cause = ex.getWrappedThrowable();
			boolean wcause = cause == null;
			String msg = "Method invocation failed during rendering of Velocity view with name '"
					+ getBeanName() + "': " + ex.getMessage() + "; reference [" + ex.getReferenceName() + "], method '"
					+ ex.getMethodName() + "'";
			logger.error(msg, wcause ? ex : cause);
			throw new NestedServletException(msg, wcause ? ex : cause);
		}
	}

	protected Template checkAndGetTemplate(String name) {
		try {
			return getTemplate(name);
		} catch (Exception e) {
			 logger.debug("Can't Loading View Template named : [" + name + "] .");
		}
		return null;
	}

	protected Template getTemplate(String name) throws Exception {
		return velocityEngine.getTemplate(name);
	}

	protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VelocityContext context = new VelocityContext(model);
		if (eventCartridge == null) {
			return context;
		}
		eventCartridge.attachToContext(context);
		return context;
	}

	@Override
	protected void initApplicationContext() throws BeansException {
		super.initApplicationContext();
		if (velocityEngine == null) {
			setVelocityEngine(autodetectVelocityEngine());
		}
		if (eventCartridge == null) {
			setEventCartridge(autodetectEventCartridge());
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

	protected String screenURI() {
		return File.separator + templates + File.separator + screen + File.separator + viewName + suffix;
	}

	protected String layoutURI() {
		return File.separator + templates + File.separator + layout + File.separator + viewName + suffix;
	}

	protected String defaultLayoutURI() {
		return File.separator + templates + File.separator + layout + File.separator + defaultLayout + suffix;
	}

	/**
	 * @param templates the templates to set
	 */
	public void setTemplates(String templates) {
		this.templates = templates;
	}

	/**
	 * @param screen the screen to set
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setDefaultLayout(String defaultLayout) {
		this.defaultLayout = defaultLayout;
	}

	public void setScreenKey(String screenKey) {
		this.screenKey = screenKey;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setEventCartridge(EventCartridge eventCartridge) {
		this.eventCartridge = eventCartridge;
	}
}