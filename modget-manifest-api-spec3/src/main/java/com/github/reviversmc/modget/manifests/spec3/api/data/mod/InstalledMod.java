package com.github.reviversmc.modget.manifests.spec3.api.data.mod;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.impl.data.mod.InstalledModImpl;

@JsonDeserialize(as = InstalledModImpl.class)
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
