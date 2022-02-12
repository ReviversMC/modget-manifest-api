package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.EnvironmentStatus;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;

public class BasicModEnvironment implements ModEnvironment {
	private ModVersionVariant parentModVersionVariant;
	private EnvironmentStatus server;
	private EnvironmentStatus client;


	public BasicModEnvironment(@JacksonInject ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;
	}


	@Override
    @JsonIgnore
	public ModVersionVariant getParentModVersionVariant() {
		return parentModVersionVariant;
	}

	@Override
    @JsonIgnore
	public void setParentModVersionVariant(ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;
	}


	@Override
	public EnvironmentStatus getServer() {
		return server;
	}

	@Override
	public void setServer(EnvironmentStatus server) {
		this.server = server;
	}


	@Override
	public EnvironmentStatus getClient() {
		return client;
	}

	@Override
	public void setClient(EnvironmentStatus client) {
		this.client = client;
	}


}
