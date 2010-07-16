package org.esupportail.sympa.domain.services;

import java.util.List;

import org.esupportail.sympa.domain.model.CreateListInfo;
import org.esupportail.sympa.domain.model.UserSympaListWithUrl;


public abstract interface IDomainService {
	public enum SympaListFields {
		address,
		owner,
		editor,
		subscriber
	};
	public List<UserSympaListWithUrl> getWhich();
	public List<UserSympaListWithUrl> getWhich(List<SympaListCriterion>criterion,boolean mathAll);
	public List<CreateListInfo> getCreateListInfo();
}
