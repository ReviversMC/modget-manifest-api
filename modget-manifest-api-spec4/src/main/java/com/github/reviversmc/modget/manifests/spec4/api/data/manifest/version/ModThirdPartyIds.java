package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModThirdPartyIdsImpl;

@JsonDeserialize(as = ModThirdPartyIdsImpl.class)
public interface ModThirdPartyIds {

	public ModVersionVariant getParentModVersionVariant();
	public void setParentModVersionVariant(ModVersionVariant parentVersion);


	public String getCurseforge();
	public void setCurseforge(String curseforge);

	public String getModrinth();
	public void setModrinth(String modrinth);

}
