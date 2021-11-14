package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModDownload;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModVersion;

public class ModVersionImpl implements ModVersion {
	private ModManifest parentManifest;
	private String version;
	private List<String> minecraftVersions;
	private String md5;
	private List<ModDownload> downloadPageUrls;
	private List<ModDownload> fileUrls;


	@JsonIgnore
	public ModVersionImpl(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;

		this.minecraftVersions = new ArrayList<>(4);
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
	public List<String> getMinecraftVersions() {
		return this.minecraftVersions;
	}

	@Override
	public void setMinecraftVersions(List<String> minecraftVersions) {
		this.minecraftVersions = minecraftVersions;
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
