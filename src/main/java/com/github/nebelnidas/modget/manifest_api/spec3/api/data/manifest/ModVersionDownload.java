package com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.ModVersionDownloadImpl;

@JsonDeserialize(as = ModVersionDownloadImpl.class)
public interface ModVersionDownload {

	public String getName();
	public void setName(String name);

	public String getUrl();
	public void setUrl(String url);
}
