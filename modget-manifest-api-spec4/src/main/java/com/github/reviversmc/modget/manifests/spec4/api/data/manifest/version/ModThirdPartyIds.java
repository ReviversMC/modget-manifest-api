package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

public interface ModThirdPartyIds {

	public ModVersionVariant getParentModVersionVariant();
	public void setParentModVersionVariant(ModVersionVariant parentVersion);


	public String getCurseforge();
	public void setCurseforge(String curseforge);

	public String getModrinth();
	public void setModrinth(String modrinth);

}
