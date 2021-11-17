package com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.main.ModThirdPartyIdsImpl;

@JsonDeserialize(as = ModThirdPartyIdsImpl.class)
public interface ModThirdPartyIds {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getCurseforge();
	public void setCurseforge(String curseforge);

	public String getModrinth();
	public void setModrinth(String modrinth);

}
