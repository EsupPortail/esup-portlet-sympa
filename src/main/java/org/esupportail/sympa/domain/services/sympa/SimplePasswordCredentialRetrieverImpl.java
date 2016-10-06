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
package org.esupportail.sympa.domain.services.sympa;


/**
 * An ICredentialRetriever for testing without using ProxyCas
 */
public class SimplePasswordCredentialRetrieverImpl implements ICredentialRetriever {
	
	String username;
	
	String password;
	
	public SympaCredential getCredentialForService(String targetService) {
		SympaCredential sc = new SympaCredential();
		sc.setId(username);
		sc.setPassword(password);
		return sc;
	}

	public TYPE getType() {
		return TYPE.password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
