package tulip.web.view;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;

import tulip.web.module.Module;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午4:12:49
 */
public abstract class AbstractView extends WebApplicationObjectSupport implements View, BeanNameAware {

	public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

	private String beanName;

	private String contentType = DEFAULT_CONTENT_TYPE;

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return this.beanName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * Prepares the view given the specified model, merging it with static
	 * attributes and a RequestContext attribute, if necessary. Delegates to
	 * renderMergedOutputModel for the actual rendering.
	 * 
	 * @see #renderMergedOutputModel
	 */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);
		prepareResponse(request, response);
		applyContentType(response);
		renderMergedOutputModel(mergedModel, request, response);
	}

	/**
	 * Creates a combined output Map (never {@code null}) that includes dynamic
	 * values and static attributes. Dynamic values take precedence over static
	 * attributes.
	 */
	protected Map<String, Object> createMergedOutputModel(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(View.PATH_VARIABLES);
		int size = (model != null) ? model.size() : 0;
		size += (pathVars != null) ? pathVars.size() : 0;
		Map<String, Object> mergedModel = new LinkedHashMap<String, Object>(size);
		if (pathVars != null) {
			mergedModel.putAll(pathVars);
		}
		if (model != null) {
			mergedModel.putAll(model);
		}
		if(!CollectionUtils.isEmpty(Module.MODULES)) {
			mergedModel.putAll(Module.MODULES);
		}
		return mergedModel;
	}

	/**
	 * Prepare the given response for rendering.
	 * <p>
	 * The default implementation applies a workaround for an IE bug when
	 * sending download content via HTTPS.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 */
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		if (!generatesDownloadContent()) {
			return;
		}
		response.setHeader("Pragma", "private");
		response.setHeader("Cache-Control", "private, must-revalidate");
	}

	/**
	 * Return whether this view generates download content (typically binary
	 * content like PDF or Excel files).
	 * <p>
	 * The default implementation returns {@code false}. Subclasses are
	 * encouraged to return {@code true} here if they know that they are
	 * generating download content that requires temporary caching on the client
	 * side, typically via the response OutputStream.
	 * 
	 * @see #prepareResponse
	 * @see javax.servlet.http.HttpServletResponse#getOutputStream()
	 */
	protected boolean generatesDownloadContent() {
		return false;
	}

	/**
	 * Subclasses must implement this method to actually render the view.
	 * <p>
	 * The first step will be preparing the request: In the JSP case, this would
	 * mean setting model objects as request attributes. The second step will be
	 * the actual rendering of the view, for example including the JSP via a
	 * RequestDispatcher.
	 * 
	 * @param model
	 *            combined output Map (never {@code null}), with dynamic values
	 *            taking precedence over static attributes
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @throws Exception
	 *             if rendering failed
	 */
	protected abstract void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * Set the content type of the response to the configured
	 * {@link #setContentType(String) content type} unless the
	 * {@link View#SELECTED_CONTENT_TYPE} request attribute is present and set
	 * to a concrete media type.
	 */
	protected void setResponseContentType(HttpServletRequest request, HttpServletResponse response) {
		MediaType mediaType = (MediaType) request.getAttribute(View.SELECTED_CONTENT_TYPE);
		if (mediaType != null && mediaType.isConcrete()) {
			response.setContentType(mediaType.toString());
		} else {
			response.setContentType(getContentType());
		}
	}

	/**
	 * Apply this view's content type as specified in the "contentType" bean
	 * property to the given response.
	 * <p>
	 * When running on Servlet 2.4, only applies the view's contentType if no
	 * content type has been set on the response before. This allows handlers to
	 * override the default content type beforehand.
	 * 
	 * @param response
	 *            current HTTP response
	 * @see #setContentType
	 */
	protected void applyContentType(HttpServletResponse response) {
		if (response.getContentType() == null) {
			response.setContentType(getContentType());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getName());
		if (getBeanName() != null) {
			sb.append(": name '").append(getBeanName()).append("'");
		} else {
			sb.append(": unnamed");
		}
		return sb.toString();
	}

}