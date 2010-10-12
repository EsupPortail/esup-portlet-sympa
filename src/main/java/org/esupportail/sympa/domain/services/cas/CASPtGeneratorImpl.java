/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.domain.services.cas;

import org.jasig.cas.client.validation.Assertion;
import org.jasig.portlet.cas.ICASProxyTicketService;


public class CASPtGeneratorImpl implements ICASPtGenerator {
	private ICASProxyTicketService casProxyTicketService;
	private ICASAssertionRetriever casAssertionRetriever;
	
	public String getCasServiceToken(String targetService) {
		Assertion assertion = casAssertionRetriever.getAssertion();
		if(assertion != null)
			return casProxyTicketService.getCasServiceToken(assertion, targetService);
		else
			return null;
	}

	/**
	 * @return the casProxyTicketService
	 */
	public ICASProxyTicketService getCasProxyTicketService() {
		return casProxyTicketService;
	}

	/**
	 * @param casProxyTicketService the casProxyTicketService to set
	 */
	public void setCasProxyTicketService(
			ICASProxyTicketService casProxyTicketService) {
		this.casProxyTicketService = casProxyTicketService;
	}

	/**
	 * @return the casAssertionRetriever
	 */
	public ICASAssertionRetriever getCasAssertionRetriever() {
		return casAssertionRetriever;
	}

	/**
	 * @param casAssertionRetriever the casAssertionRetriever to set
	 */
	public void setCasAssertionRetriever(
			ICASAssertionRetriever casAssertionRetriever) {
		this.casAssertionRetriever = casAssertionRetriever;
	}
}
