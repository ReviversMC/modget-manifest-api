package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version.ModEnvironment;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version.ModVersion;

public class ModEnvironmentImpl implements ModEnvironment {
	private ModVersion parentModVersion;
	private String server;
	private String client;


	public ModEnvironmentImpl(@JacksonInject ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;
	}


	@Override
	public ModVersion getParentModVersion() {
		return parentModVersion;
	}

	@Override
	public void setParentModVersion(ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;
	}


	@Override
	public String getServer() {
		return server;
	}

	@Override
	public void setServer(String server) {
		this.server = server;
	}


	@Override
	public String getClient() {
		return client;
	}

	@Override
	public void setClient(String client) {
		this.client = client;
	}


}
