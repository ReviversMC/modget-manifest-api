package com.github.reviversmc.modget.manifests.spec4.api.data.mod;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;

public interface InstalledMod {

	public String getId();
	public void setId(String modId);

	public String getInstalledVersion();
	public void setInstalledVersion(String version);

	public List<ModPackage> getAvailablePackages();
	public List<ModPackage> getOrDownloadAvailablePackages(List<ManifestRepository> manifestRepositories) throws Exception;
	public void addAvailablePackage(ModPackage availablePackage);
	public void setAvailablePackages(List<ModPackage> availablePackages);

}
