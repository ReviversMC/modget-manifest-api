package com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.main;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModThirdPartyIds;

public class ModThirdPartyIdsImpl implements ModThirdPartyIds {
	private ModManifest parentManifest;
	private String curseforge;
	private String modrinth;


	public ModThirdPartyIdsImpl(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
    @JsonIgnore
	public ModManifest getParentManifest() {
		return parentManifest;
	}

	@Override
    @JsonIgnore
	public void setParentManifest(ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public String getCurseforge() {
		return curseforge;
	}

	@Override
	public void setCurseforge(String curseforge) {
		this.curseforge = curseforge;
	}


	@Override
	public String getModrinth() {
		return modrinth;
	}

	@Override
	public void setModrinth(String modrinth) {
		this.modrinth = modrinth;
	}

}
