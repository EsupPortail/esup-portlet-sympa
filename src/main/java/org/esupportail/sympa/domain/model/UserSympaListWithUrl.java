/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 * @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.domain.model;

public class UserSympaListWithUrl extends UserSympaList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2955582285925647743L;

	private String listUrl;
	private String listAdminUrl;
	/**
	 * @return the listUrl
	 */
	public String getListUrl() {
		return listUrl;
	}
	/**
	 * @param listUrl the listUrl to set
	 */
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}
	/**
	 * @return the listAdminUrl
	 */
	public String getListAdminUrl() {
		return listAdminUrl;
	}
	/**
	 * @param listAdminUrl the listAdminUrl to set
	 */
	public void setListAdminUrl(String listAdminUrl) {
		this.listAdminUrl = listAdminUrl;
	}
}
