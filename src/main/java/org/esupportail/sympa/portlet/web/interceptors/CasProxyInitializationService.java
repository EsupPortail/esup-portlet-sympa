/**
 * Licensed to ESUP-Portail under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * ESUP-Portail licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esupportail.sympa.portlet.web.interceptors;

import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.sympa.domain.model.UserPreferences;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.portlet.cas.ICASProxyTicketService;
import org.springframework.util.ClassUtils;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.portlet.mvc.AbstractController;


public class CasProxyInitializationService extends HandlerInterceptorAdapter {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private UserPreferences userPreferences;
	
	private int sessionLength = 60*60*2;
	private ICASProxyTicketService proxyTicketService;
	
	/**
	 * this CasProxyInitializationService is used only if user in this roles (or if usedForRoles is null)
	 */
	private Set<String> usedForRoles;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.handler.HandlerInterceptorAdapter#preHandle(javax.portlet.PortletRequest, javax.portlet.PortletResponse, java.lang.Object)
	 */
	@Override
	protected boolean preHandle(PortletRequest request,
			PortletResponse response, Object handler) throws Exception {
		

		// No need to initialize CAS in MINIMIZED window state
		if(ClassUtils.isAssignableValue(AbstractController.class, handler)) {
			AbstractController controller = (AbstractController) handler;
			if (WindowState.MINIMIZED.equals(request.getWindowState()) && !controller.isRenderWhenMinimized()) {
                return false;
			}
		}
		
		if(!this.shouldBeUsed())
			return true;
		
		PortletSession session = request.getPortletSession();
		String ticket = proxyTicketService.haveProxyTicket(request);
		boolean doReceipt = false;
		
		if ( ticket != null ) {
			logger.debug("having a proxy ticket : "+ticket);
			if ( userPreferences.getCasReceipt() == null ) {
				logger.debug("first time PT; doing the work ...");
				doReceipt = true;
			} else {
				logger.debug("having a new ticket ? new = "+ticket+": old="+userPreferences.getCasPT());
				if ( !ticket.equals(userPreferences.getCasPT()) ) {
					logger.debug("tickets are differents; new receipt ...");
					doReceipt = true;
				} else {
					logger.debug("same ticket; nothing to be done ...");
				}
			}
		}
		
		if ( doReceipt ) {
			Assertion receipt = proxyTicketService.getProxyTicket(request);
			if ( receipt != null ) {
				logger.debug("having CAS receipt for : "+receipt.getPrincipal().getName());
				userPreferences.setCasReceipt(receipt);
				userPreferences.setCasPT(ticket);
				// increment session
				session.setMaxInactiveInterval(sessionLength);
			}
		} else {
			logger.debug("not PT received from the portal");
		}
		
		return true;
	}
	
	/**
	 * @return true if this server should be used for the current user (depending of usedForRoles) 
	 */
	public boolean shouldBeUsed() {
		if(getUsedForRoles() == null)
			return true;
		
		Set<String> userRoles = userPreferences.getUserRoles();
		if ( userRoles != null && userRoles.size() > 0 ) {
			logger.debug("user have roles");
			for (String r : userRoles) {
				logger.debug("having role : "+r);
				if (getUsedForRoles().contains(r)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return the sessionLength
	 */
	public int getSessionLength() {
		return sessionLength;
	}

	/**
	 * @param sessionLength the sessionLength to set
	 */
	public void setSessionLength(int sessionLength) {
		this.sessionLength = sessionLength;
	}

	/**
	 * @return the proxyTicketService
	 */
	public ICASProxyTicketService getProxyTicketService() {
		return proxyTicketService;
	}

	/**
	 * @param proxyTicketService the proxyTicketService to set
	 */
	public void setProxyTicketService(ICASProxyTicketService proxyTicketService) {
		this.proxyTicketService = proxyTicketService;
	}

	/**
	 * @return the userPreferences
	 */
	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	/**
	 * @param userPreferences the userPreferences to set
	 */
	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	/**
	 * @return the usedForRoles
	 */
	public Set<String> getUsedForRoles() {
		return usedForRoles;
	}
	/**
	 * @param usedForRoles the usedForRoles to set
	 */
	public void setUsedForRoles(Set<String> usedForRoles) {
		this.usedForRoles = usedForRoles;
	}

}
