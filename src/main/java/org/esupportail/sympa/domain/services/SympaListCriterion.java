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
package org.esupportail.sympa.domain.services;

import org.esupportail.sympa.domain.services.IDomainService.SympaListFields;

public class SympaListCriterion {
	private SympaListFields fieldName;
	private Object compareTo;
	
	public SympaListCriterion() {}
	public SympaListCriterion(SympaListFields fieldName, Object compareTo) {
		this.fieldName = fieldName;
		this.compareTo = compareTo;
	}
	/**
	 * @return the fieldName
	 */
	public SympaListFields getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(SympaListFields fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the compareTo
	 */
	public Object getCompareTo() {
		return compareTo;
	}
	/**
	 * @param compareTo the compareTo to set
	 */
	public void setCompareTo(Object compareTo) {
		this.compareTo = compareTo;
	}
}
