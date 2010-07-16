package org.esupportail.sympa.domain.services.cas;

import org.esupportail.sympa.domain.model.UserPreferences;
import org.jasig.cas.client.validation.Assertion;


public class CASAssertionRetrieverImpl implements ICASAssertionRetriever {
	private UserPreferences userPreferences;
	
	public Assertion getAssertion() {
		return userPreferences.getCasReceipt();
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
