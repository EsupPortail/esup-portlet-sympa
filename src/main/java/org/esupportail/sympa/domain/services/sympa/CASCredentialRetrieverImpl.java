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

import org.esupportail.sympa.domain.services.cas.ICASPtGenerator;

public class CASCredentialRetrieverImpl implements ICredentialRetriever {
	private ICASPtGenerator casPtGeneratorService;
	
	public SympaCredential getCredentialForService(String targetService) {
		String password = casPtGeneratorService.getCasServiceToken(targetService);
		if ( password != null ) {
			SympaCredential sc = new SympaCredential();
			sc.setId(targetService);
			sc.setPassword(password);
			return sc;
		}
		return null;
	}

	public TYPE getType() {
		return TYPE.cas;
	}

	/**
	 * @return the casPtGeneratorService
	 */
	public ICASPtGenerator getCasPtGeneratorService() {
		return casPtGeneratorService;
	}

	/**
	 * @param casPtGeneratorService the casPtGeneratorService to set
	 */
	public void setCasPtGeneratorService(ICASPtGenerator casPtGeneratorService) {
		this.casPtGeneratorService = casPtGeneratorService;
	}
	
}
