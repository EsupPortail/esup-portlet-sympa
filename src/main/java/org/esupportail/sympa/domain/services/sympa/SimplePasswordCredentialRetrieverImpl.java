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
