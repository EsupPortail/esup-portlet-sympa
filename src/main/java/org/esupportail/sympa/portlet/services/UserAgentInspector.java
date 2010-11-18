/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 * @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.portlet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.InitializingBean;

public class UserAgentInspector implements InitializingBean {
    
    private List<String> userAgentMobile;
    
    private final List<Pattern> patterns = new ArrayList<Pattern>();
    
    public void setUserAgentMobile(List<String> userAgentMobile) {
		this.userAgentMobile = userAgentMobile;
	}

	public void afterPropertiesSet() {
        // Compile our patterns
        for (String userAgent : userAgentMobile) {
            patterns.add(Pattern.compile(userAgent));
        }
    }

    public boolean isMobile(PortletRequest req) {
        
    	boolean isMobile = false;
    	
        // Assertions.
        if (req == null) {
            String msg = "Argument 'req' cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        String userAgent = req.getProperty("user-agent");
        if (userAgent != null && patterns.size() != 0) {
            for (Pattern pattern : patterns) {
                if (pattern.matcher(userAgent).matches()) {
                	isMobile = true;
                    break;
                }
            }
        }

        return isMobile;

    }

}
