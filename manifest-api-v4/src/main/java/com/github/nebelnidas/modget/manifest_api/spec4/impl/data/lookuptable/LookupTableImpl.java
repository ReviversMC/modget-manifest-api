package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec4.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry;

public class LookupTableImpl implements LookupTable {
	private final Repository parentRepository;
	private List<LookupTableEntry> lookupTableEntries;

	public LookupTableImpl(Repository parentRepository) {
		this.parentRepository = parentRepository;
	}

	@Override
	public Repository getParentRepository() {
		return this.parentRepository;
	}

	@Override
	public List<LookupTableEntry> getLookupTableEntries() {
		return this.lookupTableEntries;
	}

	@Override
	public void setLookupTableEntries(List<LookupTableEntry> lookupTableEntries) {
		this.lookupTableEntries = lookupTableEntries;
	}

}
