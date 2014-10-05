package tulip.web.support;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月5日 下午4:03:32
 */
public class Tulip {
	
	private boolean auto_config;
	
	private String templates_uri;
	
	private String suffix;

	/**
	 * @return the templates_uri
	 */
	public String getTemplates_uri() {
		return templates_uri;
	}

	/**
	 * @param templates_uri the templates_uri to set
	 */
	public void setTemplates_uri(String templates_uri) {
		this.templates_uri = templates_uri;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the auto_config
	 */
	public boolean isAuto_config() {
		return auto_config;
	}

	/**
	 * @param auto_config the auto_config to set
	 */
	public void setAuto_config(boolean auto_config) {
		this.auto_config = auto_config;
	}
}