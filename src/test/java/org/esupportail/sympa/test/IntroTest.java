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
