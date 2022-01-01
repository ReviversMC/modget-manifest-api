package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModDownloads;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;

public class BasicModDownloads implements ModDownloads {
	private ModVersionVariant parentModVersionVariant;
	private String modrinth;
	private String curseforge;
	private String sourceControl;
	private List<NameUrlPair> others;


	public BasicModDownloads(@JacksonInject ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;

		this.others = new ArrayList<>(0);
	}


	@Override
    @JsonIgnore
	public ModVersionVariant getParentModVersionVariant() {
		return parentModVersionVariant;
	}

	@Override
    @JsonIgnore
	public void setParentModVersionVariant(ModVersionVariant parentModVersionVariant) {
		this.parentModVersionVariant = parentModVersionVariant;
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
        if (others == null) {
            this.others.clear();
            return;
        }
		this.others = others;
	}

}
