package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

public interface ModEnvironment {

	public ModVersionVariant getParentModVersionVariant();
	public void setParentModVersionVariant(ModVersionVariant parentVersion);


	public EnvironmentStatus getServer();
	public void setServer(EnvironmentStatus supportStatus);

	public EnvironmentStatus getClient();
	public void setClient(EnvironmentStatus supportStatus);

}
