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
