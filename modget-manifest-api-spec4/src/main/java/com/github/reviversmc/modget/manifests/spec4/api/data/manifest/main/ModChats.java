package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;

public interface ModChats {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);


	public String getDiscord();
	public void setDiscord(String discord);

	public String getIrc();
	public void setIrc(String irc);

	public List<NameUrlPair> getOthers();
	public void setOthers(List<NameUrlPair> others);

}
