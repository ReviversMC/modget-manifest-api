package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModAuthor;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModChats;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.downloaders.BasicModVersionDownloader;

public class BasicModManifest implements ModManifest {
	private ModPackage parentPackage;
	private LookupTableEntry parentLookupTableEntry;
	private String manifestSpecVersion;
	private String publisher;
	private List<String> iconUrls;
	private String status;
	private List<ModPackage> updatedAlternatives;
	private String name;
	private String description;
	private List<ModAuthor> authors;
	private String home;
	private String source;
	private String issues;
	private String support;
	private String wiki;
	private ModChats chats;
	private List<ModVersion> versions;


	public BasicModManifest(@JacksonInject ModPackage parentPackage, @JacksonInject LookupTableEntry parentLookupTableEntry) {
		this.parentPackage = parentPackage;
		this.parentLookupTableEntry = parentLookupTableEntry;

		this.iconUrls = new ArrayList<>(3);
		this.updatedAlternatives = new ArrayList<>(0);
		this.authors = new ArrayList<>(1);
		this.versions = new ArrayList<>(15);
	}


	@Override
    @JsonIgnore
	public ModPackage getParentPackage() {
		return parentPackage;
	}

	@Override
    @JsonIgnore
	public void setParentPackage(ModPackage parentPackage) {
		this.parentPackage = parentPackage;
	}


	@Override
    @JsonIgnore
	public LookupTableEntry getParentLookupTableEntry() {
		return parentLookupTableEntry;
	}

	@Override
    @JsonIgnore
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
	public List<String> getIconUrls() {
		return iconUrls;
	}

	@Override
	public void setIconUrls(List<String> iconUrls) {
        if (iconUrls == null) {
            this.iconUrls.clear();
            return;
       };
		this.iconUrls = iconUrls;
	}


	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public List<ModPackage> getUpdatedAlternatives() {
		return updatedAlternatives;
	}

	@Override
	public void setUpdatedAlternatives(List<ModPackage> updatedAlternatives) {
        if (updatedAlternatives == null) {
            this.updatedAlternatives.clear();
            return;
        }
		this.updatedAlternatives = updatedAlternatives;
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
	public List<ModAuthor> getAuthors() {
		return authors;
	}

	@Override
	public void setAuthors(List<ModAuthor> authors) {
        if (authors == null) {
            this.authors.clear();
            return;
        }
		this.authors = authors;
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
	public String getSupport() {
		return support;
	}

	@Override
	public void setSupport(String support) {
		this.support = support;
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
	public ModChats getChats() {
		return chats;
	}

	@Override
	public void setChats(ModChats chats) {
		this.chats = chats;
	}


	@Override
	public List<ModVersion> getVersions() {
		return versions;
	}

	@Override
	public List<ModVersion> getOrDownloadVersions() throws Exception {
        if (versions != null || versions.size() != 0) {
            List<ModVersion> versionsNew = new ArrayList<>(15);
            for (ModVersion version : versions) {
                if (version.getVariants().isEmpty()) {
                    versionsNew.add(BasicModVersionDownloader.create().downloadModVersion(this, version.getVersion()));
                } else {
                    versionsNew.add(version);
                }
            }
            setVersions(versionsNew);
        }
		return versions;
	}

	@Override
	public void setVersions(List<ModVersion> versions) {
        if (versions == null) {
            this.versions.clear();
            return;
        }
		this.versions = versions;
	}

}

