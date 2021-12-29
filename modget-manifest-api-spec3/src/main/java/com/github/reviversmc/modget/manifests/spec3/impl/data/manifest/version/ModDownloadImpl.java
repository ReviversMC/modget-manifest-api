package com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;

public class ModDownloadImpl implements ModDownload {
	private ModVersion parentModVersion;
	private String name;
	private String url;


	public ModDownloadImpl(@JacksonInject ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;
	}


	@Override
    @JsonIgnore
	public ModVersion getParentModVersion() {
		return parentModVersion;
	}

	@Override
    @JsonIgnore
	public void setParentModVersion(ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;
	}


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

}
