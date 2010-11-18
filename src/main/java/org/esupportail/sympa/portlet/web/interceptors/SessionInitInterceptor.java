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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

import org.esupportail.sympa.domain.model.UserPreferences;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;


public class SessionInitInterceptor extends HandlerInterceptorAdapter {
	private UserPreferences userPreferences;
	private String userInfoMailAttr;
	private Set<String> availableRoles;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.portlet.handler.HandlerInterceptorAdapter#preHandle(javax.portlet.PortletRequest, javax.portlet.PortletResponse, java.lang.Object)
	 */
	@Override
	protected boolean preHandle(PortletRequest request,
			PortletResponse response, Object handler) throws Exception {
		// ensure we have a portlet session
		@SuppressWarnings("unused")
		PortletSession session = request.getPortletSession(true);
		// retrieve userid
		if ( userPreferences.getUserId() == null ) {
			// new bean
			userPreferences.setUserId(request.getRemoteUser());
			@SuppressWarnings("unchecked")
			Map<String,String> userinfo = (Map<String,String>)request.getAttribute(PortletRequest.USER_INFO);
			String mail = userinfo.get(getUserInfoMailAttr());
			if ( mail != null ) 
				mail = mail.trim();
			if ( mail.length() > 0 ) {
				userPreferences.setMail(mail);
			}
			// retrieve roles
			Set<String> userRoles = new HashSet<String>();
			for ( String r : availableRoles ) {
				if ( request.isUserInRole(r) ) {
					userRoles.add(r);
				}
			}
			userPreferences.setUserRoles(userRoles);
		}
		
		return true;
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
	 * @return the userInfoMailAttr
	 */
	public String getUserInfoMailAttr() {
		return userInfoMailAttr;
	}
	/**
	 * @param userInfoMailAttr the userInfoMailAttr to set
	 */
	public void setUserInfoMailAttr(String userInfoMailAttr) {
		this.userInfoMailAttr = userInfoMailAttr;
	}
	/**
	 * @return the availableRoles
	 */
	public Set<String> getAvailableRoles() {
		return availableRoles;
	}
	/**
	 * @param availableRoles the availableRoles to set
	 */
	public void setAvailableRoles(Set<String> availableRoles) {
		this.availableRoles = availableRoles;
	}
	
}
