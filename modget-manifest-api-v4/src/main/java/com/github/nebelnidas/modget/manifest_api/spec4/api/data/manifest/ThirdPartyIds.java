package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ThirdPartyIdsImpl;

@JsonDeserialize(as = ThirdPartyIdsImpl.class)
public interface ThirdPartyIds {

	public String getCurseforge();
	public void setCurseforge(String curseforge);

	public String getModrinth();
	public void setModrinth(String modrinth);

}