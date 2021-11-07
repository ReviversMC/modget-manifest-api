package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.main;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.main.ModAuthorImpl;

@JsonDeserialize(as = ModAuthorImpl.class)
public interface ModAuthor {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getName();
	public void setName(String name);

}
