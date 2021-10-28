package com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.lookuptable.LookupTableEntryImpl;

@JsonDeserialize(as = LookupTableEntryImpl.class)
public interface LookupTableEntry {

	public LookupTable getParentLookupTable();

	public String getId();
	public void setId(String id);

	public List<String> getNames();
	public void setNames(List<String> names);

	public List<String> getPackages();
	public void setPackages(List<String> packages);

	public List<String> getTags();
	public void setTags(List<String> tags);

}
