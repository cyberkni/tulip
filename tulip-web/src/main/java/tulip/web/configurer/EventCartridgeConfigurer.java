package tulip.web.configurer;

import org.apache.velocity.app.event.EventCartridge;

/**
 * 用于增强Velocity渲染功能的模板事件桥接器配置管理器。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午6:36:01
 */
public interface EventCartridgeConfigurer {
	EventCartridge getEventCartridge();
}