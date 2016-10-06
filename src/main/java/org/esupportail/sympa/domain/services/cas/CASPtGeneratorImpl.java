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
