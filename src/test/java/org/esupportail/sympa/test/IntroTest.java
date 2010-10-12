/**
 * Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
 * Copyright (C) 2010 Esup Portail http://www.esup-portail.org
 * @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
 * @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
 * @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>
 *
 * Licensed under the GPL License, (please see the LICENCE file)
 */

package org.esupportail.sympa.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.esupportail.sympa.domain.model.UserSympaList;
import org.esupportail.sympa.domain.services.SympaListCriterion;
import org.esupportail.sympa.domain.services.IDomainService.SympaListFields;
import org.springframework.beans.DirectFieldAccessor;


public class IntroTest extends TestCase {
	
	public void testIntro() throws Exception {
		UserSympaList item = new UserSympaList();
		item.setEditor(true);
		item.setOwner(false);
		item.setSubscriber(true);
		
		List<SympaListCriterion> crit = new ArrayList<SympaListCriterion>();
		crit.add(new SympaListCriterion(SympaListFields.editor,new Boolean(true)));
		crit.add(new SympaListCriterion(SympaListFields.owner,new Boolean(true)));
		crit.add(new SympaListCriterion(SympaListFields.subscriber,new Boolean(true)));
		
		DirectFieldAccessor accessor = new DirectFieldAccessor(item);
		System.out.println(accessor.getPropertyValue("editor").equals(new Boolean(false)));
		System.out.println(matchCriterion(item, crit));
	}
	
	private boolean matchCriterion(UserSympaList item, List<SympaListCriterion> crits) {
		if ( item == null || crits == null || crits.size() <= 0 ) return false;
		DirectFieldAccessor accessor = new DirectFieldAccessor(item);
		int results = 0;
		for ( SympaListCriterion c : crits ) {
			try {
				if ( accessor.isReadableProperty(c.getFieldName().name()) ) {
					Object o = accessor.getPropertyValue(c.getFieldName().name());
					if ( o == null ) {
						// case compare to null object 
						if ( c.getCompareTo() == null ) results++;
					} else {
						if ( o.equals(c.getCompareTo()) ) results++;
					}
				} else {
					System.err.printf("%1$s not accessible\n",c.getFieldName().name());
				}
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}
		return (results == crits.size() ) ? true : false;
	}
}
