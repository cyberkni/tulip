package tulip.web.view;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午7:01:30
 */
public class TulipViewResolver extends AbstractCachingViewResolver implements InitializingBean {
	
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

	/** 模板的扩展名称 */
	protected String suffix;
	
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		TulipView tulipView = new TulipView();
		tulipView.setTemplates(templates);
		tulipView.setScreen(screen);
		tulipView.setLayout(layout);
		tulipView.setDefaultLayout(defaultLayout);
		tulipView.setScreenKey(screenKey);
		tulipView.setSuffix(suffix);
		tulipView.setViewName(viewName);
		return applyLifecycleMethods(viewName, tulipView);
	}
	
	private TulipView applyLifecycleMethods(String viewName, TulipView view) {
		return (TulipView) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
	}
	
	protected void init() throws Exception {
		
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			init();
		} catch (Exception e) {
			logger.error("TulipViewResolver Init Error.", e);
		}
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

	/**
	 * @param defaultLayout the defaultLayout to set
	 */
	public void setDefaultLayout(String defaultLayout) {
		this.defaultLayout = defaultLayout;
	}

	/**
	 * @param screenKey the screenKey to set
	 */
	public void setScreenKey(String screenKey) {
		this.screenKey = screenKey;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}