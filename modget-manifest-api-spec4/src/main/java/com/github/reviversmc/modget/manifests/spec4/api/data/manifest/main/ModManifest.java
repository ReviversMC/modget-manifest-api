package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.ModManifestImpl;

@JsonDeserialize(as = ModManifestImpl.class)
public interface ModManifest {

	public ModPackage getParentPackage();
	public void setParentPackage(ModPackage parentPackage);

	public LookupTableEntry getParentLookupTableEntry();
	public void setParentLookupTableEntry(LookupTableEntry parentLookupTableEntry);


	public String getManifestSpecVersion();
	public void setManifestSpecVersion(String manifestSpecVersion);

	public String getPublisher();
	public void setPublisher(String publisher);

	public List<String> getIconUrls();
	public void setIconUrls(List<String> iconUrls);

	public String getStatus();
	public void setStatus(String status);

	public List<ModPackage> getUpdatedAlternatives();
	public void setUpdatedAlternatives(List<ModPackage> updatedAlternatives);

	public String getName();
	public void setName(String name);

	public String getDescription();
	public void setDescription(String description);

	public List<ModAuthor> getAuthors();
	public void setAuthors(List<ModAuthor> authors);

	public String getHome();
	public void setHome(String home);

	public String getSource();
	public void setSource(String source);

	public String getIssues();
	public void setIssues(String issues);

	public String getSupport();
	public void setSupport(String support);

	public String getWiki();
	public void setWiki(String wiki);

	public ModChats getChats();
	public void setChats(ModChats chats);

	public List<ModVersion> getVersions();
	public List<ModVersion> getOrDownloadVersions() throws Exception;
	public void setVersions(List<ModVersion> versions);
}
