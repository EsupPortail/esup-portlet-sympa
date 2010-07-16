package org.esupportail.sympa.domain.services.sympa;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.esupportail.sympa.domain.model.UserSympaListWithUrl;
import org.esupportail.sympa.domain.services.sympa.ICredentialRetriever.TYPE;
import org.sympa.client.ws.axis.v544.ListType;
import org.sympa.client.ws.axis.v544.SympaPort_PortType;
import org.sympa.client.ws.axis.v544.SympaSOAP;
import org.sympa.client.ws.axis.v544.SympaSOAPLocator;


public class SympaServerAxisWsImpl extends AbstractSympaServer {
	private int timeout = 5000;
	private String endPointUrl;
	
	@Override
	public List<UserSympaListWithUrl> getWhich() {
		// first of all; get a fresh new port
		SympaPort_PortType SympaPort_PortType = null;
		try {
			SympaPort_PortType = getPort();
		} catch (MalformedURLException e) {
			logger.error("unable to get a new SympaPort_PortType",e);
			return null;
		} catch (ServiceException e) {
			logger.error("unable to get a new SympaPort_PortType",e);
			return null;
		} catch (RemoteException e) {
			logger.error("unable to get a new SympaPort_PortType",e);
			return null;
		}
		if ( SympaPort_PortType == null ) {
			logger.error("unable to get a new SympaPort_PortType");
			return null;
		}
		// do the which
		ListType[] whichList = null;
		try {
			whichList = SympaPort_PortType.complexWhich();
		} catch (RemoteException e) {
			logger.error("complexWhich() failed !",e);
			return null;
		}
		List<UserSympaListWithUrl> result = new ArrayList<UserSympaListWithUrl>();
		if ( whichList != null ) {
			for ( int idx = 0; idx < whichList.length; idx++ ) {
				ListType l = whichList[idx];
				UserSympaListWithUrl item = new UserSympaListWithUrl();
				item.setEditor(l.getIsEditor());
				item.setOwner(l.getIsOwner());
				item.setSubscriber(l.getIsSubscriber());
				item.setAddress(l.getListAddress());
				item.setHomepage(l.getHomepage());
				item.setSubject(l.getSubject());
				//  append various urls
				item.setListUrl(generateListUrl(item.getHomepage()));
				item.setListAdminUrl(generateListAdminUrl(item.getAddress()));
				result.add(item);
			}
		}
		return result;
	}
	
	private SympaPort_PortType getPort() throws MalformedURLException, ServiceException, RemoteException {
		SympaSOAP locator = new SympaSOAPLocator();
		((SympaSOAPLocator)locator).setMaintainSession(true); // mandatory for cookie after login
		SympaPort_PortType port = locator.getSympaPort(new URL(getEndPointUrl()));
		// set a timeout on port (10 seconds)
		((org.apache.axis.client.Stub)port).setTimeout(getTimeout());
		// now authenticate
		TYPE credsType = getCredentialRetriever().getType();
		SympaCredential creds = getCredentialRetriever().getCredentialForService(endPointUrl);
		if ( creds == null ) {
			logger.error("unable to retrieve credential for service "+endPointUrl);
			return null;
		}
		switch ( credsType ) {
		case cas:
			String tmp = port.casLogin(creds.getPassword());
			if ( logger.isDebugEnabled() ) {
				logger.debug("CAS authentication ok : "+tmp);
			}
			break;
		case password:
			String tmp2 = port.login(creds.getId(), creds.getPassword());
			if ( logger.isDebugEnabled() ) {
				logger.debug("PASSWORD authentication ok : "+tmp2);
			}
			break;
		case trusted:
			logger.info("TODO; must use authenticateAndRun ....");
			break;
		}
		return port;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the endPointUrl
	 */
	public String getEndPointUrl() {
		return endPointUrl;
	}

	/**
	 * @param endPointUrl the endPointUrl to set
	 */
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	
}
