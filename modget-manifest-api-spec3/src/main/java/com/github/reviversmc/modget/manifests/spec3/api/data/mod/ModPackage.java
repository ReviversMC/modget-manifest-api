package com.github.reviversmc.modget.manifests.spec3.api.data.mod;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.impl.data.mod.ModPackageImpl;

@JsonDeserialize(as = ModPackageImpl.class)
public interface ModPackage {

	public String getPackageId();
	public void setPackageId(String packageId);

	public String getPublisher();
	public void setPublisher(String publisherName);

	public String getModId();
	public void setModId(String modId);

	public List<ModManifest> getManifests();
	public List<ModManifest> getOrDownloadManifests(List<ManifestRepository> repos) throws Exception;
	public void addManifest(ModManifest manifest);
	public void setManifests(List<ModManifest> manifests);

	public String getVersion();
	public void setVersion(String version);

}
