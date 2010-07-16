package org.esupportail.sympa.domain.services.sympa;


public interface ICredentialRetriever {
	public enum TYPE {
		cas,
		password,
		trusted
	};
	public SympaCredential getCredentialForService(String targetService);
	public TYPE getType();
}
