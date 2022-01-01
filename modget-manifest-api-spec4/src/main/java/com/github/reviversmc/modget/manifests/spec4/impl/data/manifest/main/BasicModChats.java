package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModChats;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;

public class BasicModChats implements ModChats {
	private ModManifest parentManifest;
	private String discord;
	private String irc;
	private List<NameUrlPair> others;


	public BasicModChats(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;

		this.others = new ArrayList<>(0);
	}


	@Override
    @JsonIgnore
	public ModManifest getParentManifest() {
		return parentManifest;
	}

	@Override
    @JsonIgnore
	public void setParentManifest(ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public String getDiscord() {
		return discord;
	}

	@Override
	public void setDiscord(String discord) {
		this.discord = discord;
	}


	@Override
	public String getIrc() {
		return irc;
	}

	@Override
	public void setIrc(String irc) {
		this.irc = irc;
	}


	@Override
	public List<NameUrlPair> getOthers() {
		return others;
	}

	@Override
	public void setOthers(List<NameUrlPair> others) {
        if (others == null) {
            this.others.clear();
        }
		this.others = others;
	}

}
