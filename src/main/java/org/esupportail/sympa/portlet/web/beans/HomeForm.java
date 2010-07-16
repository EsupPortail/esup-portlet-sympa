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
