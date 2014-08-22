package tulip.data.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月20日 下午5:24:32
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Column {
	String name();
}