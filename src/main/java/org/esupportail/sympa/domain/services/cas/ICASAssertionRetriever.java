package org.esupportail.sympa.domain.services.cas;

import org.jasig.cas.client.validation.Assertion;

public interface ICASAssertionRetriever {

	public Assertion getAssertion();
}
