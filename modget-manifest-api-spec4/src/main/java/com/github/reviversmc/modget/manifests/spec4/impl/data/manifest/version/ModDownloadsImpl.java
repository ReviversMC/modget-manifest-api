package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModDownloads;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;

public class ModDownloadsImpl implements ModDownloads {
	private ModVersion parentModVersion;
	private String modrinth;
	private String curseforge;
	private String sourceControl;
	private List<NameUrlPair> others;


	public ModDownloadsImpl(@JacksonInject ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;

		this.others = new ArrayList<>(0);
	}


	@Override
	public ModVersion getParentModVersion() {
		return parentModVersion;
	}

	@Override
	public void setParentModVersion(ModVersion parentModVersion) {
		this.parentModVersion = parentModVersion;
	}


	@Override
	public String getModrinth() {
		return modrinth;
	}

	@Override
	public void setModrinth(String modrinth) {
		this.modrinth = modrinth;
	}


	@Override
	public String getCurseforge() {
		return curseforge;
	}

	@Override
	public void setCurseforge(String curseforge) {
		this.curseforge = curseforge;
	}


	@Override
	public String getSourceControl() {
		return sourceControl;
	}

	@Override
	public void setSourceControl(String sourceControl) {
		this.sourceControl = sourceControl;
	}


	@Override
	public List<NameUrlPair> getOther() {
		return others;
	}

	@Override
	public void addOther(NameUrlPair other) {
		others.add(other);
	}

	@Override
	public void setOther(List<NameUrlPair> others) {
		this.others = others;
	}

}
