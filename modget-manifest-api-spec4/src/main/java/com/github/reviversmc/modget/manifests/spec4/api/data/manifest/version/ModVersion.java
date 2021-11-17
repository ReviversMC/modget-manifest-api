package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModVersionImpl;

@JsonDeserialize(as = ModVersionImpl.class)
public interface ModVersion {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getVersion();
	public void setVersion(String version);

	public List<String> getLoaders();
	public void setLoaders(List<String> loaders);

	public List<String> getMinecraftVersions() ;
	public void setMinecraftVersions(List<String> minecraftVersions);

	public ModEnvironment getEnvironment();
	public void setEnvironment(ModEnvironment environment);

	public String getChannel();
	public void setChannel(String channel);

	public List<ModPackage> getDepends();
	public void setDepends(List<ModPackage> modPackages);

	public List<ModPackage> getBundles();
	public void setBundles(List<ModPackage> modPackages);

	public List<ModPackage> getBreaks();
	public void setBreaks(List<ModPackage> modPackages);

	public List<ModPackage> getConflicts();
	public void setConflicts(List<ModPackage> modPackages);

	public List<ModPackage> getRecommends();
	public void setRecommends(List<ModPackage> modPackages);

	public ModThirdPartyIds getThirdPartyIds();
	public void setThirdPartyIds(ModThirdPartyIds thirdPartyIds);

	public String getLicense();
	public void setLicense(String license);

	public String getFileType();
	public void setFileType(String fileType);

	public String getMd5();
	public void setMd5(String md5);

	public ModDownloads getDownloadPageUrls();
	public void setDownloadPageUrls(ModDownloads downloadPageUrls);

	public ModDownloads getFileUrls();
	public void setFileUrls(ModDownloads fileUrls);

}
