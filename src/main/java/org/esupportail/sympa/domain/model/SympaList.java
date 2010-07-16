package org.esupportail.sympa.domain.model;

import java.io.Serializable;

public class SympaList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224626282671137797L;
	
	/**
	 * list webpage 
	 */
	private String homepage;
	/**
	 * list email address
	 */
	private String address;
	/**
	 * list subject
	 */
	private String subject;
	/**
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}
	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
