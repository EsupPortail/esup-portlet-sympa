package org.esupportail.sympa.domain.model;

public class UserSympaList extends SympaList {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632209925580241799L;

	private boolean owner;
	private boolean editor;
	private boolean subscriber;
	/**
	 * @return the isOwner
	 */
	public boolean isOwner() {
		return owner;
	}
	/**
	 * @param isOwner the isOwner to set
	 */
	public void setOwner(boolean isOwner) {
		this.owner = isOwner;
	}
	/**
	 * @return the isEditor
	 */
	public boolean isEditor() {
		return editor;
	}
	/**
	 * @param isEditor the isEditor to set
	 */
	public void setEditor(boolean isEditor) {
		this.editor = isEditor;
	}
	/**
	 * @return the isSubscriber
	 */
	public boolean isSubscriber() {
		return subscriber;
	}
	/**
	 * @param isSubscriber the isSubscriber to set
	 */
	public void setSubscriber(boolean isSubscriber) {
		this.subscriber = isSubscriber;
	}
}
