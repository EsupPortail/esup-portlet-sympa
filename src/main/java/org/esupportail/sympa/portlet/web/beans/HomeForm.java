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
