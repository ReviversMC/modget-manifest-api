package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;

public class BasicModThirdPartyIds implements ModThirdPartyIds {
	private ModVersionVariant parentModVersionVariant;
	private String modrinth;
	private String curseforge;


	public BasicModThirdPartyIds(@JacksonInject ModVersionVariant parentModVersionVariant) {
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
	public String getModrinth() {
		return modrinth;
	}

	@Override
	public void setModrinth(String modrinth) {
		this.modrinth = modrinth;
	}


	@Override
	public String getCurseforge() {
		return curseforge;
	}

	@Override
	public void setCurseforge(String curseforge) {
		this.curseforge = curseforge;
	}

}
