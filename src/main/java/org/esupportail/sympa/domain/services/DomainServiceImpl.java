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
package org.esupportail.sympa.domain.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.esupportail.sympa.domain.model.CreateListInfo;
import org.esupportail.sympa.domain.model.UserSympaList;
import org.esupportail.sympa.domain.model.UserSympaListWithUrl;
import org.esupportail.sympa.domain.services.sympa.AbstractSympaServer;
import org.springframework.beans.DirectFieldAccessor;


public class DomainServiceImpl implements IDomainService {
	private Map <String,AbstractSympaServer> serverList;
	private static Log logger = LogFactory.getLog(DomainServiceImpl.class);
	
	public List<UserSympaListWithUrl> getWhich() {
		// watchout; user centric ...
		Collection<AbstractSympaServer> srvList = getServerList().values();
		List<UserSympaListWithUrl> result = new ArrayList<UserSympaListWithUrl>();
		for ( AbstractSympaServer s : srvList ) {
			List<UserSympaListWithUrl> srvResult = s.getWhich();
			if ( srvResult != null && srvResult.size() > 0 ) {
				result.addAll(srvResult);
			}
		}
		// default sort on list address
		sortResults(result);
		return result;
	}

	public List<UserSympaListWithUrl> getWhich(List<SympaListCriterion> criterions, boolean matchAll) {
		List<UserSympaListWithUrl> sympaList = getWhich();
		if ( criterions == null || criterions.size() <= 0 ) return sympaList;
		List<UserSympaListWithUrl> filteredList = new ArrayList<UserSympaListWithUrl>();
		for ( UserSympaListWithUrl item : sympaList ) {
			if ( matchCriterions(item, criterions, matchAll) ) {
				filteredList.add(item);
			}
		}
		return filteredList;
	}
	

	public List<CreateListInfo> getCreateListInfo() {
		Collection<AbstractSympaServer> srvList = getServerList().values();
		List<CreateListInfo> result = new ArrayList<CreateListInfo>();
		for ( AbstractSympaServer s : srvList ) {
			CreateListInfo infos = s.getCreateListInfo();
			if ( infos != null )
				result.add(infos);
		}
		return result;
	}

	private boolean matchCriterions(UserSympaList item, List<SympaListCriterion> crits,boolean matchAll) {
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
					logger.debug("");
				}
			} catch ( Exception e) {
				logger.error("exception raised while introspecting object ",e);
			}
		}
		if ( matchAll ) {
			return (results == crits.size() ) ? true : false;
		} else {
			return (results > 0 ) ? true : false;
		}
	}
	//protected boolean have
	// sorting
	private void sortResults(List<UserSympaListWithUrl> toSort) {
		Collections.sort(toSort, new UserSympaListComparator());
	}
	
	class UserSympaListComparator implements Comparator<UserSympaList> {
		boolean sortOrder; // true mean ascending
		SympaListFields sortOn;
		public UserSympaListComparator() {
			this.sortOrder = true;
			this.sortOn = SympaListFields.address;
		}
		public UserSympaListComparator(SympaListFields field,boolean order) {
			this.sortOrder = order;
			this.sortOn = field;
		}

		public int compare(UserSympaList o1, UserSympaList o2) {
			int result = 0;
			switch (sortOn) {
			case address :
				result = compareString(o1.getAddress(), o2.getAddress());
				break;
			case owner :
				result = compareBoolean(o1.isOwner(), o2.isOwner());
				break;
			case editor:
				result = compareBoolean(o1.isEditor(), o2.isEditor());
				break;
			case subscriber:
				result = compareBoolean(o1.isSubscriber(), o2.isSubscriber());
				break;
			}
			return result;
		}
		private int compareBoolean(boolean b1, boolean b2) {
			int result = 0;
			if ( (b1 && b2) || (!b1 && !b2) ) return 0;
			if ( sortOrder ) {
				result = ( b1 ) ? 1 : -1;
			} else {
				result = ( b1 ) ? -1 : 1; 
			}
			return result;
		}
		private int compareString(String s1, String s2) {
			if ( s1 == null || s2 == null ) return 0;
			int result = 0;
			if ( sortOrder ) {
				result = s1.compareTo(s2);
			} else {
				result = s2.compareTo(s1);
			}
			return result;
		}
	}
	/**
	 * @return the serverList
	 */
	public Map<String, AbstractSympaServer> getServerList() {
		Map<String, AbstractSympaServer> serverListToUse = new HashMap<String, AbstractSympaServer>();
		for(String serverKey: serverList.keySet()) {
			if(serverList.get(serverKey).shouldBeUsed()) {
				logger.debug("Add this server to the list for the current user : " + serverKey);		
				serverListToUse.put(serverKey, serverList.get(serverKey));
			}
		}
		return serverListToUse;
	}

	public String getHomeUrl() {
		String homeUrl="#";
		for(String serverKey: serverList.keySet()) {
			if(serverList.get(serverKey).shouldBeUsed()) {
				homeUrl=serverList.get(serverKey).getHomeUrl();			
			}
		}
		return homeUrl;
	}

	/**
	 * @param serverList the serverList to set
	 */
	public void setServerList(Map<String, AbstractSympaServer> serverList) {
		this.serverList = serverList;
	}

	

}
