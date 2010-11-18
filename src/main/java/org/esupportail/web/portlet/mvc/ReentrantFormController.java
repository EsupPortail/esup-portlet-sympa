package org.esupportail.web.portlet.mvc;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.sympa.portlet.services.UserAgentInspector;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.handler.PortletSessionRequiredException;
import org.springframework.web.portlet.mvc.AbstractCommandController;

/**
 * @author ofranco
 * Override referenceData 
 * Override initBinderFor Custom data binding
 * Override handleAction 
 * Override 
 */
public class ReentrantFormController extends AbstractCommandController {
	
	private Log logger = LogFactory.getLog(ReentrantFormController.class);
	
	private String viewName;
	
	protected UserAgentInspector userAgentInspector;
	private final String ESUPSYMPA_WIDE_VIEW = "esupsympaWideView";
	private final String ESUPSYMPA_NARROW_VIEW = "esupsympaNarrowView";
	private final String ESUPSYMPA_MOBILE_VIEW = "esupsympaMobileView";
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.AbstractCommandController#handleRender(javax.portlet.RenderRequest, javax.portlet.RenderResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@SuppressWarnings("unchecked")
	@Override
	
	protected ModelAndView handleRender(RenderRequest request,
			RenderResponse response, Object command, BindException errors)
			throws Exception {
		// Fetch errors model as starting point, containing form object under
		// "commandName", and corresponding Errors instance under internal key.
		Map model = errors.getModel();
		
		// Merge reference data into model, if any.
		Map referenceData = referenceData(request, errors.getTarget(), errors);
		if (referenceData != null) {
			model.putAll(referenceData);
		}

		// Merge control attributes into model, if any
		/*.
		if (controlModel != null) {
			model.putAll(controlModel);
		}*/
		// Trigger rendering of the specified view, using the final model.
		//return new ModelAndView(getViewName(), model);
		
	    if(userAgentInspector.isMobile(request)) {
	    	
			return new ModelAndView(ESUPSYMPA_MOBILE_VIEW, model);
	    } else {
	    	WindowState state = request.getWindowState();
			if (WindowState.MAXIMIZED.equals(state)) {
				return new ModelAndView(ESUPSYMPA_WIDE_VIEW, model);
			} else {
				return new ModelAndView(ESUPSYMPA_NARROW_VIEW, model);
			}
	    }		
	}
	
	@SuppressWarnings("unchecked")
	public Map referenceData(PortletRequest request, Object command, Errors errors) throws Exception {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.BaseCommandController#getCommand(javax.portlet.PortletRequest)
	 * will be called ONCE in AbstractCommandController
	 */
	@Override
	protected Object getCommand(PortletRequest request) throws Exception {
		// maybe call super.getCommand for intanciating commanClass !
		//super.getCommand(request);
		if ( logger.isDebugEnabled() )
			logger.debug("entering getCommand ");
		PortletSession session = request.getPortletSession(false);
		if (session == null) {
			throw new PortletSessionRequiredException("Could not obtain portlet session");
		}
		Object command = session.getAttribute(getRenderCommandSessionAttributeName());
		if (command != null) {
			if ( logger.isDebugEnabled() ) logger.debug("having command from session ...");
			return alterCommand(command,request);
		}
		if ( logger.isDebugEnabled() ) logger.debug("generating new command ...");
		return newCommand(request);
	}
	
	public Object alterCommand(Object command, PortletRequest request) throws Exception {
		return command;
	}
	
	public Object newCommand(PortletRequest request) throws Exception {
		return super.createCommand();
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.BaseCommandController#initBinder(javax.portlet.PortletRequest, org.springframework.web.portlet.bind.PortletRequestDataBinder)
	 */
	/*
	@Override
	protected void initBinder(PortletRequest request,
			PortletRequestDataBinder binder) throws Exception {
		// register cutome editor form "date"
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true,10));
		// TODO ? skip pageNumer and maxRecordPerPage
		//binder.
	}*/

	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.BaseCommandController#getRenderCommandSessionAttributeName()
	 */
	@Override
	protected String getRenderCommandSessionAttributeName() {
		String attr = this.getClass().getName();
		attr += ".command";
		if ( logger.isDebugEnabled() ) {
			logger.debug("render Command session attribute name = "+attr);
		}
		return attr;
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.mvc.BaseCommandController#getRenderErrorsSessionAttributeName()
	 */
	@Override
	protected String getRenderErrorsSessionAttributeName() {
		String attr = this.getClass().getName();
		attr += ".errors";
		if ( logger.isDebugEnabled() ) {
			logger.debug("render Error session attribute name = "+attr);
		}
		return attr;
	}
	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}
	/**
	 * @param viewName the viewName to set
	 */
	public String setViewName(String viewName) {
		return this.viewName = viewName;
	}

	@Override
	protected void handleAction(ActionRequest request, ActionResponse response,
			Object command, BindException bindException) throws Exception {
		handleActionRequest(request,response,command,bindException);
	}
	
	public void handleActionRequest(ActionRequest request, ActionResponse response,
			Object command, BindException bindException) throws Exception {
		
	}
	
	public void setUserAgentInspector(UserAgentInspector userAgentInspector) {
		this.userAgentInspector = userAgentInspector;
	}
}
