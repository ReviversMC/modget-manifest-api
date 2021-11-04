package com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.ModVersionImpl;

@JsonDeserialize(as = ModVersionImpl.class)
public interface ModVersion {

	public Manifest getParentManifest();
	@Deprecated
	public void setParentManifest(Manifest parentManifest);

	public String getVersion();
	public void setVersion(String version);

	public List<String> getMinecraftVersions() ;
	public void setMinecraftVersions(List<String> minecraftVersions);

	public String getMd5();
	public void setMd5(String md5);

	public List<ModVersionDownload> getDownloadPageUrls();
	public void setDownloadPageUrls(List<ModVersionDownload> downloadPageUrls);

	public List<ModVersionDownload> getFileUrls();
	public void setFileUrls(List<ModVersionDownload> fileUrls);

}
