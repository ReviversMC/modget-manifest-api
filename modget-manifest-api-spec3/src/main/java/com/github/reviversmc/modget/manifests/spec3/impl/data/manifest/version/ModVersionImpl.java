package com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;

public class ModVersionImpl implements ModVersion {
	private ModManifest parentManifest;
	private String version;
	private List<String> loaders;
	private String md5;
	private List<String> minecraftVersions;
	private List<ModPackage> depends;
	private List<ModPackage> breaks;
	private List<ModPackage> recommends;
	private List<ModDownload> downloadPageUrls;
	private List<ModDownload> fileUrls;


	public ModVersionImpl(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;

		this.loaders = new ArrayList<>(1);
		this.minecraftVersions = new ArrayList<>(4);
		this.depends = new ArrayList<>(2);
		this.breaks = new ArrayList<>(2);
		this.recommends = new ArrayList<>(1);
		this.downloadPageUrls = new ArrayList<>(3);
		this.fileUrls = new ArrayList<>(3);
	}


	@Override
	public ModManifest getParentManifest() {
		return this.parentManifest;
	}

	@Override
	public void setParentManifest(ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public List<String> getLoaders() {
		return loaders;
	}

	@Override
	public void setLoaders(List<String> loaders) {
		this.loaders = loaders;
	}


	@Override
	public String getMd5() {
		return this.md5;
	}

	@Override
	public void setMd5(String md5) {
		this.md5 = md5;
	}


	@Override
	public List<String> getMinecraftVersions() {
		return this.minecraftVersions;
	}

	@Override
	public void setMinecraftVersions(List<String> minecraftVersions) {
		this.minecraftVersions = minecraftVersions;
	}


	@Override
	public List<ModPackage> getDepends() {
		return depends;
	}

	@Override
	public void setDepends(List<ModPackage> depends) {
		this.depends = depends;
	}


	@Override
	public List<ModPackage> getBreaks() {
		return breaks;
	}

	@Override
	public void setBreaks(List<ModPackage> breaks) {
		this.breaks = breaks;
	}


	@Override
	public List<ModPackage> getRecommends() {
		return recommends;
	}

	@Override
	public void setRecommends(List<ModPackage> recommends) {
		this.recommends = recommends;
	}


	@Override
	public List<ModDownload> getDownloadPageUrls() {
		return this.downloadPageUrls;
	}

	@Override
	public void setDownloadPageUrls(List<ModDownload> downloadPageUrls) {
		this.downloadPageUrls = downloadPageUrls;
	}


	@Override
	public List<ModDownload> getFileUrls() {
		return this.fileUrls;
	}

	@Override
	public void setFileUrls(List<ModDownload> fileUrls) {
		this.fileUrls = fileUrls;
	}

}
