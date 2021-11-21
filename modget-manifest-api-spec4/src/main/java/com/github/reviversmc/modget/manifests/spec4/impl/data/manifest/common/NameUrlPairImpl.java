package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.common;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;

public class NameUrlPairImpl implements NameUrlPair {
	private String name;
	private String url;


	public NameUrlPairImpl(@JacksonInject String name, @JacksonInject String url) {
		this.name = name;
		this.url = url;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

}
