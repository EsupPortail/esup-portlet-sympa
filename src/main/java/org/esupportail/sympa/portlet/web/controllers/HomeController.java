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

package org.esupportail.sympa.portlet.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.esupportail.sympa.domain.model.CreateListInfo;
import org.esupportail.sympa.domain.model.UserSympaListWithUrl;
import org.esupportail.sympa.domain.services.IDomainService;
import org.esupportail.sympa.domain.services.SympaListCriterion;
import org.esupportail.sympa.domain.services.IDomainService.SympaListFields;
import org.esupportail.sympa.portlet.web.beans.HomeForm;
import org.esupportail.web.portlet.mvc.ReentrantFormController;
import org.springframework.validation.Errors;


public class HomeController extends ReentrantFormController {
	
	private IDomainService domainService;

	@Override
	public Object newCommand(PortletRequest request) throws Exception {
		HomeForm  form = (HomeForm)super.createCommand();
		form.setEditor(true);
		form.setOwner(true);
		form.setSubscriber(true);
		return form;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map referenceData(PortletRequest request, Object command,
			Errors errors) throws Exception {
		HomeForm form = (HomeForm)command;
		List<UserSympaListWithUrl> sympaList = domainService.getWhich(formToCriterion(form),false);
		List<CreateListInfo> createList = domainService.getCreateListInfo();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sympaList", sympaList);
		map.put("createList", createList);
		return map;
	}
	
	private List<SympaListCriterion> formToCriterion(HomeForm form) {
		if ( form == null ) return null;
		List<SympaListCriterion> crits = new ArrayList<SympaListCriterion>();
		if ( form.isEditor() )
			crits.add(new SympaListCriterion(SympaListFields.editor,new Boolean(form.isEditor())));
		if ( form.isOwner() ) 
			crits.add(new SympaListCriterion(SympaListFields.owner,new Boolean(form.isOwner())));
		if ( form.isSubscriber() )
			crits.add(new SympaListCriterion(SympaListFields.subscriber,new Boolean(form.isSubscriber())));
		return crits;
	}
	/**
	 * @return the domainService
	 */
	public IDomainService getDomainService() {
		return domainService;
	}

	/**
	 * @param domainService the domainService to set
	 */
	public void setDomainService(IDomainService domainService) {
		this.domainService = domainService;
	}

}
