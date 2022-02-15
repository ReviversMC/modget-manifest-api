package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.common.ModLoader;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;

public interface ModVersionVariant {

	public ModVersion getParentVersion();
	public void setParentVersion(ModVersion parentVersion);


	public List<ModLoader> getLoaders();
	public void setLoaders(List<ModLoader> loaders);

	public List<String> getMinecraftVersions() ;
	public void setMinecraftVersions(List<String> minecraftVersions);

	public ModEnvironment getEnvironment();
	public void setEnvironment(ModEnvironment environment);

	public ReleaseChannel getChannel();
	public void setChannel(ReleaseChannel channel);

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

	public ModFileType getFileType();
	public void setFileType(ModFileType fileType);

	public String getMd5();
	public void setMd5(String md5);

	public ModDownloads getDownloadPageUrls();
	public void setDownloadPageUrls(ModDownloads downloadPageUrls);

	public ModDownloads getFileUrls();
	public void setFileUrls(ModDownloads fileUrls);

}
