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
