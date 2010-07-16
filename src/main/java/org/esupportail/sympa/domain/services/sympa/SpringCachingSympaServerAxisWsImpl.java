package org.esupportail.sympa.domain.services.sympa;

import org.springframework.beans.factory.InitializingBean;

public class SpringCachingSympaServerAxisWsImpl extends
		CachingSympaServerAxisWsImpl implements InitializingBean {

	public SpringCachingSympaServerAxisWsImpl() {
		super();
	}

	public void afterPropertiesSet() throws Exception {
		initCache();
	}

}
