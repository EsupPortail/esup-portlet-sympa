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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.axis.transport.http.HTTPConstants;
import org.esupportail.sympa.domain.model.UserSympaListWithUrl;
import org.esupportail.sympa.domain.services.sympa.ICredentialRetriever.TYPE;
import org.sympa.client.ws.axis.v544.SOAPStub;
import org.sympa.client.ws.axis.v544.SympaPort_PortType;
import org.sympa.client.ws.axis.v544.SympaSOAP;
import org.sympa.client.ws.axis.v544.SympaSOAPLocator;

public class SympaServerAxisWsImpl extends AbstractSympaServer {
	
	private int timeout = 5000;
	
	private String endPointUrl;
	
	// must be session scope so the bean of type SympaServerAxisWsImpl is session scope
	private SympaPort_PortType port = null; 
	
	@Override
	public List<UserSympaListWithUrl> getWhich() {
		// first of all; get a fresh new port if needed
		if(port!=null) {
			try {
				String checkCookie = port.checkCookie();
				if(checkCookie == null || "nobody".equals(checkCookie))
					port = null;
			} catch (RemoteException e) {
				logger.debug("port is no more usable, we reinitate it",e);
				port = null;
			}
		}
		if(port == null) {
			try {
				port = getPort();
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
		}
		if (port == null ) {
			logger.error("unable to get a new SympaPort_PortType");
			return null;
		}
		// do the which
		//ListType[] whichList = null;
		String[] whichList = null;
		try {
			/* BUG 
			 *    """org.xml.sax.SAXException:  No deserializer for {http://www.w3.org/2001/XMLSchema}anyType""" 
			 * with Axis
			 * so we use port.which() ... 
			whichList = SympaPort_PortType.complexWhich();
			*/
			whichList = port.which();
		} catch (RemoteException e) {
			logger.error("complexWhich() failed !",e);
			return null;
		}
		List<UserSympaListWithUrl> result = new ArrayList<UserSympaListWithUrl>();
		if ( whichList != null ) {
			for ( int idx = 0; idx < whichList.length; idx++ ) {
				/*
				ListType l = whichList[idx];
				UserSympaListWithUrl item = new UserSympaListWithUrl();
				item.setEditor(l.getIsEditor());
				item.setOwner(l.getIsOwner());
				item.setSubscriber(l.getIsSubscriber());
				item.setAddress(l.getListAddress());
				item.setHomepage(l.getHomepage());
				item.setSubject(l.getSubject());
				*/
				String l = whichList[idx];
				Map<String, String> listeInfos = this.stringToMap(l);
				UserSympaListWithUrl item = new UserSympaListWithUrl();
				item.setEditor(listeInfos.get("isEditor").equals("1"));
				item.setOwner(listeInfos.get("isOwner").equals("1"));
				item.setSubscriber(listeInfos.get("isSubscriber").equals("1"));
				item.setAddress(listeInfos.get("listAddress"));
				item.setHomepage(listeInfos.get("homepage"));
				item.setSubject(listeInfos.get("subject"));
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
			((SOAPStub)port)._setProperty(HTTPConstants.HEADER_COOKIE,
				    "sympa_session=" + tmp);
			if ( logger.isDebugEnabled() ) {
				logger.debug("CAS authentication ok : "+tmp);
			}
			break;
		case password:
			String tmp2 = port.login(creds.getId(), creds.getPassword());
			((SOAPStub)port)._setProperty(HTTPConstants.HEADER_COOKIE,
				    "sympa_session=" + tmp2);
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
	
	
	
	protected static Map<String, String> stringToMap(String input) {  
		Map<String, String> map = new HashMap<String, String>();  

		String[] nameValuePairs = input.split(";");  
		for (String nameValuePair : nameValuePairs) {  
			String[] nameValue = nameValuePair.split("=");  
			try {  
				map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(  
						nameValue[1], "UTF-8") : "");  
			} catch (UnsupportedEncodingException e) {  
				throw new RuntimeException("This method requires UTF-8 encoding support", e);  
			}  
		}  

		return map;  
	}  
	
}
