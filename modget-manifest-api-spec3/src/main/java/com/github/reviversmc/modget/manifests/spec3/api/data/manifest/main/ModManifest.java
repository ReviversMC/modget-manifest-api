package com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.main.ModManifestImpl;

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

	public String getId();
	public void setId(String id);

	public String getModType();
	public void setModType(String modType);

	public String getSide();
	public void setSide(String side);

	public String getName();
	public void setName(String name);

	public String getDescription();
	public void setDescription(String description);

	public String getLicense();
	public void setLicense(String license);

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

	public String getChat();
	public void setChat(String chat);

	public ModThirdPartyIds getThirdPartyIds();
	public void setThirdPartyIds(ModThirdPartyIds thirdPartyIds);

	public List<ModVersion> getDownloads();
	public void setDownloads(List<ModVersion> downloads);

}
