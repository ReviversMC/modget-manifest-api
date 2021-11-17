package com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.version.ModDownloadImpl;

@JsonDeserialize(as = ModDownloadImpl.class)
public interface ModDownload {

	public ModVersion getParentModVersion();
	public void setParentModVersion(ModVersion parentVersion);
	

	public String getName();
	public void setName(String name);

	public String getUrl();
	public void setUrl(String url);
}
