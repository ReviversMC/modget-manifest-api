package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.common.NameUrlPairImpl;

@JsonDeserialize(as = NameUrlPairImpl.class)
public interface NameUrlPair {

	public String getName();
	public void setName(String name);

	public String getUrl();
	public void setUrl(String url);

}
