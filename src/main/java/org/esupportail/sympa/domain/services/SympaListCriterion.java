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
