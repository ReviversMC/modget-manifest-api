package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ThirdPartyIds;

public class ThirdPartyIdsImpl implements ThirdPartyIds {
	private String curseforge;
	private String modrinth;


	public ThirdPartyIdsImpl() {
	}


	@Override
	public String getCurseforge() {
		return this.curseforge;
	}

	@Override
	public void setCurseforge(String curseforge) {
		this.curseforge = curseforge;
	}

	@Override
	public String getModrinth() {
		return this.modrinth;
	}

	@Override
	public void setModrinth(String modrinth) {
		this.modrinth = modrinth;
	}

}
