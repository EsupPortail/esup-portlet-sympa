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
package org.esupportail.sympa.portlet.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.esupportail.sympa.domain.model.CreateListInfo;
import org.esupportail.sympa.domain.model.UserSympaListWithUrl;
import org.esupportail.sympa.domain.services.DomainServiceImpl;
import org.esupportail.sympa.domain.services.IDomainService;
import org.esupportail.sympa.domain.services.SympaListCriterion;
import org.esupportail.sympa.domain.services.IDomainService.SympaListFields;
import org.esupportail.sympa.domain.services.sympa.AbstractSympaServer;
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
		String homeUrl = domainService.getHomeUrl();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("homeUrl",homeUrl);
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
