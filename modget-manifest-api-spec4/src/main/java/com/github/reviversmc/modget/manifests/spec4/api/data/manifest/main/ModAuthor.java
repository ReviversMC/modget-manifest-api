package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main;

public interface ModAuthor {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getName();
	public void setName(String name);

}
