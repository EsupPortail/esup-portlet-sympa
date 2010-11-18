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

package org.esupportail.sympa.portlet.web.beans;

import java.io.Serializable;

public class HomeForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8079455173431690805L;

	private boolean isOwner;
	private boolean isEditor;
	private boolean isSubscriber;
	/**
	 * @return the isOwner
	 */
	public boolean isOwner() {
		return isOwner;
	}
	/**
	 * @param isOwner the isOwner to set
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	/**
	 * @return the isEditor
	 */
	public boolean isEditor() {
		return isEditor;
	}
	/**
	 * @param isEditor the isEditor to set
	 */
	public void setEditor(boolean isEditor) {
		this.isEditor = isEditor;
	}
	/**
	 * @return the isSubscriber
	 */
	public boolean isSubscriber() {
		return isSubscriber;
	}
	/**
	 * @param isSubscriber the isSubscriber to set
	 */
	public void setSubscriber(boolean isSubscriber) {
		this.isSubscriber = isSubscriber;
	}
	
}
