package com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;

public interface LookupTable {

	public Repository getParentRepository();

	public List<LookupTableEntry> getLookupTableEntries();
	public void setLookupTableEntries(List<LookupTableEntry> lookupTableEntries);

}
