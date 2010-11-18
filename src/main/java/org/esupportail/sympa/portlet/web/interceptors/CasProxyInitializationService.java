/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 * @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.portlet.web.interceptors;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.sympa.domain.model.UserPreferences;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.portlet.cas.ICASProxyTicketService;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;


public class CasProxyInitializationService extends HandlerInterceptorAdapter {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private UserPreferences userPreferences;
	
	private int sessionLength = 60*60*2;
	private ICASProxyTicketService proxyTicketService;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.handler.HandlerInterceptorAdapter#preHandle(javax.portlet.PortletRequest, javax.portlet.PortletResponse, java.lang.Object)
	 */
	@Override
	protected boolean preHandle(PortletRequest request,
			PortletResponse response, Object handler) throws Exception {
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

}
