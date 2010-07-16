package org.esupportail.sympa.domain.services.sympa;

import java.util.Set;

public interface IUserIdentityRetriever {
	public String getMail();
	public String getId();
	public Set<String> getRoles();
}
