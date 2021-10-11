package com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.manifest.ModVersionDownloadImpl;

@JsonDeserialize(as = ModVersionDownloadImpl.class)
public interface ModVersionDownload {

	public String getName();
	public void setName(String name);

	public String getUrl();
	public void setUrl(String url);
}
