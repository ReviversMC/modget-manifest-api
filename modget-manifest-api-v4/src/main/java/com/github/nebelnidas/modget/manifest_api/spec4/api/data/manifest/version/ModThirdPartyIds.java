package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.version.ModThirdPartyIdsImpl;

@JsonDeserialize(as = ModThirdPartyIdsImpl.class)
public interface ModThirdPartyIds {

	public ModVersion getParentModVersion();
	public void setParentModVersion(ModVersion parentVersion);


	public String getCurseforge();
	public void setCurseforge(String curseforge);

	public String getModrinth();
	public void setModrinth(String modrinth);

}
