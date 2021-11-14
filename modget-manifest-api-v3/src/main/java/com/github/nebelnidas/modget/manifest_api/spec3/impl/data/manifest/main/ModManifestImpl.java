package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.main;

import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.common.PackageIdVersionPair;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModThirdPartyIds;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModVersion;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;

public class ModManifestImpl implements ModManifest {
	private ModPackage parentPackage;
	private LookupTableEntry parentLookupTableEntry;
	private String manifestSpecVersion;
	private String publisher;
	private String id;
	private List<String> loader;
	private String modType;
	private String side;
	private String name;
	private String description;
	private String license;
	private String home;
	private String source;
	private String issues;
	private String wiki;
	private String support;
	private String chat;
	private List<PackageIdVersionPair> depends;
	private List<PackageIdVersionPair> breaks;
	private List<PackageIdVersionPair> recommends;
	private ModThirdPartyIds thirdPartyIds;
	private List<ModVersion> downloads;


	public ModManifestImpl(@JacksonInject ModPackage parentPackage, @JacksonInject LookupTableEntry parentLookupTableEntry) {
		this.parentPackage = parentPackage;
		this.parentLookupTableEntry = parentLookupTableEntry;
	}


	@Override
	public ModPackage getParentPackage() {
		return parentPackage;
	}

	@Override
	public void setParentPackage(ModPackage parentPackage) {
		this.parentPackage = parentPackage;
	}


	@Override
	public LookupTableEntry getParentLookupTableEntry() {
		return parentLookupTableEntry;
	}

	@Override
	public void setParentLookupTableEntry(LookupTableEntry parentLookupTableEntry) {
		this.parentLookupTableEntry = parentLookupTableEntry;
	}


	@Override
	public String getManifestSpecVersion() {
		return manifestSpecVersion;
	}

	@Override
	public void setManifestSpecVersion(String manifestSpecVersion) {
		this.manifestSpecVersion = manifestSpecVersion;
	}


	@Override
	public String getPublisher() {
		return publisher;
	}

	@Override
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public List<String> getLoader() {
		return loader;
	}

	@Override
	public void setLoader(List<String> loader) {
		this.loader = loader;
	}


	@Override
	public String getModType() {
		return modType;
	}

	@Override
	public void setModType(String modType) {
		this.modType = modType;
	}


	@Override
	public String getSide() {
		return side;
	}

	@Override
	public void setSide(String side) {
		this.side = side;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String getLicense() {
		return license;
	}

	@Override
	public void setLicense(String license) {
		this.license = license;
	}


	@Override
	public String getHome() {
		return home;
	}

	@Override
	public void setHome(String home) {
		this.home = home;
	}


	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}


	@Override
	public String getIssues() {
		return issues;
	}

	@Override
	public void setIssues(String issues) {
		this.issues = issues;
	}


	@Override
	public String getWiki() {
		return wiki;
	}

	@Override
	public void setWiki(String wiki) {
		this.wiki = wiki;
	}


	@Override
	public String getSupport() {
		return support;
	}

	@Override
	public void setSupport(String support) {
		this.support = support;
	}


	@Override
	public String getChat() {
		return chat;
	}

	@Override
	public void setChat(String chat) {
		this.chat = chat;
	}


	@Override
	public List<PackageIdVersionPair> getDepends() {
		return depends;
	}

	@Override
	public void setDepends(List<PackageIdVersionPair> depends) {
		this.depends = depends;
	}


	@Override
	public List<PackageIdVersionPair> getBreaks() {
		return breaks;
	}

	@Override
	public void setBreaks(List<PackageIdVersionPair> breaks) {
		this.breaks = breaks;
	}


	@Override
	public List<PackageIdVersionPair> getRecommends() {
		return recommends;
	}

	@Override
	public void setRecommends(List<PackageIdVersionPair> recommends) {
		this.recommends = recommends;
	}


	@Override
	public ModThirdPartyIds getThirdPartyIds() {
		return thirdPartyIds;
	}

	@Override
	public void setThirdPartyIds(ModThirdPartyIds thirdPartyIds) {
		this.thirdPartyIds = thirdPartyIds;
	}


	@Override
	public List<ModVersion> getDownloads() {
		return downloads;
	}

	@Override
	public void setDownloads(List<ModVersion> downloads) {
		this.downloads = downloads;
	}

}

