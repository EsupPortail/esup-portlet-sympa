/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.jasig.cas.client.validation.Assertion;

public class UserPreferences implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -901626160941617836L;
	private String userId;
	private String mail;
	private Set<String> userRoles = new HashSet<String>();
	private Assertion casReceipt;
	private String casPT;
	private String password;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the userRoles
	 */
	public Set<String> getUserRoles() {
		return userRoles;
	}
	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}
	/**
	 * @return the casReceipt
	 */
	public Assertion getCasReceipt() {
		return casReceipt;
	}
	/**
	 * @param casReceipt the casReceipt to set
	 */
	public void setCasReceipt(Assertion casReceipt) {
		this.casReceipt = casReceipt;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the casPT
	 */
	public String getCasPT() {
		return casPT;
	}
	/**
	 * @param casPT the casPT to set
	 */
	public void setCasPT(String casPT) {
		this.casPT = casPT;
	}
}
