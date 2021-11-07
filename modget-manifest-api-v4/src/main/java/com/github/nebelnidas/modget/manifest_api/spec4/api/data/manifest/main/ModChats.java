package com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.main;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.common.NameUrlPair;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.main.ModChatsImpl;

@JsonDeserialize(as = ModChatsImpl.class)
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
