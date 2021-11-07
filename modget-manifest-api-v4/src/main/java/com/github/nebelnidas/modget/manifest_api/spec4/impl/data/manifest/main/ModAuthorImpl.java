package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.main;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.main.ModAuthor;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.main.ModManifest;

public class ModAuthorImpl implements ModAuthor {
	private ModManifest parentManifest;
	private String name;


	public ModAuthorImpl(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public ModManifest getParentManifest() {
		return parentManifest;
	}

	@Override
	public void setParentManifest(ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
}
