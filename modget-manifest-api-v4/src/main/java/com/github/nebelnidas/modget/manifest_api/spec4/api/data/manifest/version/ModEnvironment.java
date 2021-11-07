package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.version.ModEnvironmentImpl;

@JsonDeserialize(as = ModEnvironmentImpl.class)
public interface ModEnvironment {

	public ModVersion getParentModVersion();
	public void setParentModVersion(ModVersion parentVersion);


	public String getServer();
	public void setServer(String supportStatus);

	public String getClient();
	public void setClient(String supportStatus);

}
