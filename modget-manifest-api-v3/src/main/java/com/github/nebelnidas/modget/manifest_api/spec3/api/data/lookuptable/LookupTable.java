package com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Repository;

public interface LookupTable {

	public Repository getParentRepository();

	public List<LookupTableEntry> getLookupTableEntries();
	public void setLookupTableEntries(List<LookupTableEntry> lookupTableEntries);

}
