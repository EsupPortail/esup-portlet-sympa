/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.domain.services.sympa;

import java.util.Set;

import org.esupportail.sympa.domain.model.UserPreferences;


public class UserIdentityRetrieverImpl implements IUserIdentityRetriever {

	private UserPreferences userPreferences;

	public String getId() {
		return userPreferences.getUserId();
	}


	public String getMail() {
		return userPreferences.getMail();
	}

	public Set<String> getRoles() {
		return userPreferences.getUserRoles();
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
