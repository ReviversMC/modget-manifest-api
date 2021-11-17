package com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.version.ModVersionImpl;

@JsonDeserialize(as = ModVersionImpl.class)
public interface ModVersion {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getVersion();
	public void setVersion(String version);

	public List<String> getMinecraftVersions() ;
	public void setMinecraftVersions(List<String> minecraftVersions);

	public String getMd5();
	public void setMd5(String md5);

	public List<ModDownload> getDownloadPageUrls();
	public void setDownloadPageUrls(List<ModDownload> downloadPageUrls);

	public List<ModDownload> getFileUrls();
	public void setFileUrls(List<ModDownload> fileUrls);

}
