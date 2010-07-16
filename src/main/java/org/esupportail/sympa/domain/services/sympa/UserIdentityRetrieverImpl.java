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
