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
