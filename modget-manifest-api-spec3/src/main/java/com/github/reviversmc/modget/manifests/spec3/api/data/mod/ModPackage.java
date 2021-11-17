package com.github.reviversmc.modget.manifests.spec3.api.data.mod;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;

public interface ModPackage {

	public String getPackageId();
	public void setPackageId(String packageId);

	public String getPublisher();
	public void setPublisher(String publisherName);

	public String getModId();
	public void setModId(String modId);

	public List<ModManifest> getManifests();
	public void addManifest(ModManifest manifest);
	public void setManifests(List<ModManifest> manifests);

}