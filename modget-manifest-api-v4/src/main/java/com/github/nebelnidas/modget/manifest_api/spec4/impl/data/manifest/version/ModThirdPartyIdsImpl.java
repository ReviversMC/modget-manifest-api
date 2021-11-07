package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.version;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version.ModThirdPartyIds;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version.ModVersion;

public class ModThirdPartyIdsImpl implements ModThirdPartyIds {
	private ModVersion parentModVersion;
	private String modrinth;
	private String curseforge;


	public ModThirdPartyIdsImpl(@JacksonInject ModVersion parentModVersion) {
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
