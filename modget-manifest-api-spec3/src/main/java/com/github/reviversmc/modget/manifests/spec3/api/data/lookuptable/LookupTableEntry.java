package com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable.LookupTableEntryImpl;

@JsonDeserialize(as = LookupTableEntryImpl.class)
public interface LookupTableEntry {

	public LookupTable getParentLookupTable();
	public void setParentLookupTable(LookupTable parentLookupTable);


	public String getId();
	public void setId(String id);

	public List<String> getNames();
	public void setNames(List<String> names);

	public List<ModPackage> getPackages();
	public List<ModPackage> getOrDownloadPackages() throws Exception;
	public void setPackages(List<ModPackage> packages);

	public List<String> getTags();
	public void setTags(List<String> tags);

}
