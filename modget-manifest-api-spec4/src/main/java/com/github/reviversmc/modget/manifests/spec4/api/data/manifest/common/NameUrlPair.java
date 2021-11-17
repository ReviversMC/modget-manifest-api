package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.common.NameUrlPairImpl;

@JsonDeserialize(as = NameUrlPairImpl.class)
public interface NameUrlPair {

	public String getName();
	public void setName(String name);

	public String getUrl();
	public void setUrl(String url);

}
