package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.version;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModDownload;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModVersion;

public class ModDownloadImpl implements ModDownload {
	private ModVersion parentModVersion;
	private String name;
	private String url;


	public ModDownloadImpl() {
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
