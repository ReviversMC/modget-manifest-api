package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModDownloadsImpl;

@JsonDeserialize(as = ModDownloadsImpl.class)
public interface ModDownloads {

	public ModVersion getParentModVersion();
	public void setParentModVersion(ModVersion parentVersion);


	public String getModrinth();
	public void setModrinth(String url);

	public String getCurseforge();
	public void setCurseforge(String url);

	public String getSourceControl();
	public void setSourceControl(String url);

	public List<NameUrlPair> getOther();
	public void addOther(NameUrlPair nameUrlPair);
	public void setOther(List<NameUrlPair> nameUrlPairs);

}
