package analytics.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import analytics.core.dao.AppDAO;
import analytics.core.dao.EventDAO;
import analytics.core.dao.LabelDAO;
import analytics.core.dao.ModelDAO;
import analytics.core.dao.StatsDAO;
import analytics.core.dao.UserDAO;
import analytics.core.service.syn.SynSource;
import analytics.core.service.syn.SynSource.SynSourceInitialize;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 下午1:12:04
 */
public abstract class BaseService implements SynSourceInitialize, InitializingBean, BeanNameAware, ApplicationContextAware, ApplicationEventPublisherAware {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier(value="appDAO")
	protected AppDAO appDAO;
	@Autowired
	@Qualifier(value="eventDAO")
	protected EventDAO eventDAO;
	@Autowired
	@Qualifier(value="labelDAO")
	protected LabelDAO labelDAO;
	@Autowired
	@Qualifier(value="modelDAO")
	protected ModelDAO modelDAO;
	@Autowired
	@Qualifier(value="statsDAO")
	protected StatsDAO statsDAO;
	@Autowired
	@Qualifier(value="userDAO")
	protected UserDAO userDAO;
	
	protected ApplicationContext application;
	protected ApplicationEventPublisher publisher;
	protected String name;
	
	@Override
	public void initialize(SynSource source) {
		source.setAppDAO(appDAO);
		source.setEventDAO(eventDAO);
		source.setLabelDAO(labelDAO);
		source.setModelDAO(modelDAO);
		source.setStatsDAO(statsDAO);
		source.setUserDAO(userDAO);
	}
	
	@Override
	public final void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public final void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
		publisher.publishEvent(new ApplicationEvent(this) {
			private static final long serialVersionUID = 1L;
		});
	}

	@Override
	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		application = applicationContext;
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			initialize();
		} catch (Exception e) {
			log.error("Service Named : " + name + " Initialize Error.", e);
		}
	}

	protected void initialize() throws Exception {
		
	}

	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}
	
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public void setLabelDAO(LabelDAO labelDAO) {
		this.labelDAO = labelDAO;
	}
	
	public void setModelDAO(ModelDAO modelDAO) {
		this.modelDAO = modelDAO;
	}
	
	public void setStatsDAO(StatsDAO statsDAO) {
		this.statsDAO = statsDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}