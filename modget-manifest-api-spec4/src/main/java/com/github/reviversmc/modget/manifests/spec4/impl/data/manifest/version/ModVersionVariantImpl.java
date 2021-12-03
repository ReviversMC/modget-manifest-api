package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModDownloads;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;

public class ModVersionVariantImpl implements ModVersionVariant {
	private ModVersion parentVersion;
	private List<String> loaders;
	private List<String> minecraftVersions;
	private ModEnvironment environment;
	private String channel;
	private List<ModPackage> depends;
	private List<ModPackage> bundles;
	private List<ModPackage> breaks;
	private List<ModPackage> conflicts;
	private List<ModPackage> recommends;
	private ModThirdPartyIds thirdPartyIds;
	private String license;
	private String fileType;
	private String md5;
	private ModDownloads downloadPageUrls;
	private ModDownloads fileUrls;


	public ModVersionVariantImpl(@JacksonInject ModVersion parentVersion) {
		this.parentVersion = parentVersion;

		this.loaders = new ArrayList<>(1);
		this.minecraftVersions = new ArrayList<>(4);
		this.depends = new ArrayList<>(2);
		this.bundles = new ArrayList<>(0);
		this.breaks = new ArrayList<>(2);
		this.conflicts = new ArrayList<>(1);
		this.recommends = new ArrayList<>(1);
	}


	@Override
	public ModVersion getParentVersion() {
		return parentVersion;
	}

	@Override
	public void setParentVersion(ModVersion parentVersion) {
		this.parentVersion = parentVersion;
	}


	@Override
	public List<String> getLoaders() {
		return loaders;
	}

	@Override
	public void setLoaders(List<String> loaders) {
        if (loaders == null) {
            this.loaders.clear();
            return;
        }
		this.loaders = loaders;
	}


	@Override
	public List<String> getMinecraftVersions() {
		return minecraftVersions;
	}

	@Override
	public void setMinecraftVersions(List<String> minecraftVersions) {
        if (minecraftVersions == null) {
            this.minecraftVersions.clear();
            return;
        }
		this.minecraftVersions = minecraftVersions;
	}


	@Override
	public ModEnvironment getEnvironment() {
		return environment;
	}

	@Override
	public void setEnvironment(ModEnvironment environment) {
		this.environment = environment;
	}


	@Override
	public String getChannel() {
		return channel;
	}

	@Override
	public void setChannel(String channel) {
		this.channel = channel;
	}


	@Override
	public List<ModPackage> getDepends() {
		return depends;
	}

	@Override
	public void setDepends(List<ModPackage> depends) {
        if (depends == null) {
            this.depends.clear();
            return;
        }
		this.depends = depends;
	}


	@Override
	public List<ModPackage> getBundles() {
		return bundles;
	}

	@Override
	public void setBundles(List<ModPackage> bundles) {
        if (bundles == null) {
            this.bundles.clear();
            return;
        }
		this.bundles = bundles;
	}


	@Override
	public List<ModPackage> getBreaks() {
		return breaks;
	}

	@Override
	public void setBreaks(List<ModPackage> breaks) {
        if (breaks == null) {
            this.breaks.clear();
            return;
        }
		this.breaks = breaks;
	}


	@Override
	public List<ModPackage> getConflicts() {
		return conflicts;
	}

	@Override
	public void setConflicts(List<ModPackage> conflicts) {
        if (conflicts == null) {
            this.conflicts.clear();
            return;
        }
		this.conflicts = conflicts;
	}


	@Override
	public List<ModPackage> getRecommends() {
		return recommends;
	}

	@Override
	public void setRecommends(List<ModPackage> recommends) {
        if (recommends == null) {
            this.recommends.clear();
            return;
        }
		this.recommends = recommends;
	}


	@Override
	public ModThirdPartyIds getThirdPartyIds() {
		return thirdPartyIds;
	}

	@Override
	public void setThirdPartyIds(ModThirdPartyIds thirdPartyIds) {
		this.thirdPartyIds = thirdPartyIds;
	}


	@Override
	public String getLicense() {
		return license;
	}

	@Override
	public void setLicense(String license) {
		this.license = license;
	}


	@Override
	public String getFileType() {
		return fileType;
	}

	@Override
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	@Override
	public String getMd5() {
		return md5;
	}

	@Override
	public void setMd5(String md5) {
		this.md5 = md5;
	}


	@Override
	public ModDownloads getDownloadPageUrls() {
		return downloadPageUrls;
	}

	@Override
	public void setDownloadPageUrls(ModDownloads downloadPageUrls) {
		this.downloadPageUrls = downloadPageUrls;
	}


	@Override
	public ModDownloads getFileUrls() {
		return fileUrls;
	}

	@Override
	public void setFileUrls(ModDownloads fileUrls) {
		this.fileUrls = fileUrls;
	}


}
