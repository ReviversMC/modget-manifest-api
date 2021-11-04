package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ModVersionDownload;

public class ModVersionDownloadImpl implements ModVersionDownload {
	private String name;
	private String url;


	public ModVersionDownloadImpl() {
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