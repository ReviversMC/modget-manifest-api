package com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.LookupTableEntryImpl;

@JsonDeserialize(as = LookupTableEntryImpl.class)
public interface LookupTableEntry {

	public LookupTable getParentLookupTable();
	public void setParentLookupTable(LookupTable parentLookupTable);


	public String getId();
	public void setId(String id);

	public List<String> getNames();
	public void setNames(List<String> names);

	public List<String> getTags();
	public void setTags(List<String> tags);

	public List<ModPackage> getPackages();
	public void setPackages(List<ModPackage> packages);

}
