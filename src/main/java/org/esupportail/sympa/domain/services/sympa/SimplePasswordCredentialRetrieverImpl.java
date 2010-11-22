/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.domain.services.sympa;


/**
 * An ICredentialRetriever for testing without using ProxyCas
 */
public class SimplePasswordCredentialRetrieverImpl implements ICredentialRetriever {
	
	String username;
	
	String password;
	
	public SympaCredential getCredentialForService(String targetService) {
		SympaCredential sc = new SympaCredential();
		sc.setId(username);
		sc.setPassword(password);
		return sc;
	}

	public TYPE getType() {
		return TYPE.password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
