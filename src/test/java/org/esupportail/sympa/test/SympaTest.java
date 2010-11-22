/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.test;

import java.net.URL;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.transport.http.HTTPConstants;
import org.sympa.client.ws.axis.v544.ListType;
import org.sympa.client.ws.axis.v544.SympaPort_PortType;
import org.sympa.client.ws.axis.v544.SympaSOAP;
import org.sympa.client.ws.axis.v544.SympaSOAPLocator;
import org.sympa.client.ws.axis.v544.SOAPStub;

public class SympaTest extends TestCase {


	public void testSympa() throws Exception {
		
		Properties prop = new Properties();
		prop.load(ClassLoader.getSystemResourceAsStream("test.properties"));

		String testSympaUrl = prop.getProperty("testSympaUrl");
		String testSympaUsername = prop.getProperty("testSympaUsername");
		String testSympaPassword = prop.getProperty("testSympaPassword");
		
		System.out.println("testSympaUrl:" + testSympaUrl);
		System.out.println("testSympaUsername:" + testSympaUsername);
		System.out.println("testSympaPassword:" + testSympaPassword);
		
		SympaSOAP locator = new SympaSOAPLocator();
		((SympaSOAPLocator)locator).setMaintainSession(true); // mandatory for cookie after login
		
		URL sympaURL = new URL(testSympaUrl);
		SympaPort_PortType port = locator.getSympaPort(sympaURL);
		
		String cookie = port.login(testSympaUsername, testSympaPassword);
		System.out.println("cookie:" + cookie);
		// force keeping cookie
		((SOAPStub)port)._setProperty(HTTPConstants.HEADER_COOKIE,
			    "sympa_session=" + cookie);
		
		System.out.println("port.checkCookie():" + port.checkCookie());
		
		/* BUG 
		 *    """org.xml.sax.SAXException:  No deserializer for {http://www.w3.org/2001/XMLSchema}anyType""" 
		 * with Axis
		 * so we use port.which() ...
		ListType[] which = port.complexWhich();
		System.out.println(which.length);
		for ( ListType l : which ) {
			System.out.printf("%1$s %2$s %3$s\n",l.getListAddress(),l.getSubject(),l.getHomepage());
		}
		*/
		
		String[] simpleWhich = port.which();
		System.out.println("simpleWhich.length:" + simpleWhich.length);
		
		for(String list: simpleWhich) {
			System.out.println("list:" + list);
		}

	}

}
