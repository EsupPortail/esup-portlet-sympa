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
