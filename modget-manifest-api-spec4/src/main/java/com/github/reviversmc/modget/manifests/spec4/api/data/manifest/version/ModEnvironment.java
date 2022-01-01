package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

public interface ModEnvironment {

	public ModVersionVariant getParentModVersionVariant();
	public void setParentModVersionVariant(ModVersionVariant parentVersion);


	public String getServer();
	public void setServer(String supportStatus);

	public String getClient();
	public void setClient(String supportStatus);

}
