package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;

public class ModEnvironmentImpl implements ModEnvironment {
	private ModVersionVariant parentModVersionVariant;
	private String server;
	private String client;


	public ModEnvironmentImpl(@JacksonInject ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;
	}


	@Override
	public ModVersionVariant getParentModVersionVariant() {
		return parentModVersionVariant;
	}

	@Override
	public void setParentModVersionVariant(ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;
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
