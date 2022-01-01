package com.github.reviversmc.modget.manifests.spec4.api.data.mod;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;

public interface ModPackage {

	public String getPackageId();
	public void setPackageId(String packageId);

	public String getPublisher();
	public void setPublisher(String publisherName);

	public String getModId();
	public void setModId(String modId);

	public List<String> getLoaders();
	public void setLoaders(List<String> modLoaders);

	public List<ModManifest> getManifests();
	public List<ModManifest> getOrDownloadManifests(List<ManifestRepository> repos) throws Exception;
	public void addManifest(ModManifest manifest);
	public void setManifests(List<ModManifest> manifests);

	public String getVersion();
	public void setVersion(String version);

}
