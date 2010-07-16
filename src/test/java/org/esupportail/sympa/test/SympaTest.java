package org.esupportail.sympa.test;

import java.net.URL;
import java.util.Properties;

import junit.framework.TestCase;

import org.sympa.client.ws.axis.v544.ListType;
import org.sympa.client.ws.axis.v544.SympaPort_PortType;
import org.sympa.client.ws.axis.v544.SympaSOAP;
import org.sympa.client.ws.axis.v544.SympaSOAPLocator;

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
		// set a timeout on port (10 seconds)
		((org.apache.axis.client.Stub)port).setTimeout(10000);
		
		String cookie = port.login(testSympaUsername, testSympaPassword);
		System.out.println("cookie:" + cookie);

		System.out.println("port.checkCookie():" + port.checkCookie());
		
		String[] simpleWhich = port.which();
		System.out.println(simpleWhich.length);
		ListType[] which = port.complexWhich();
		System.out.println(which.length);
		for ( ListType l : which ) {
			System.out.printf("%1$s %2$s %3$s\n",l.getListAddress(),l.getSubject(),l.getHomepage());
		}

	}

}
