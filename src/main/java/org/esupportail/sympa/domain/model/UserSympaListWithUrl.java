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
