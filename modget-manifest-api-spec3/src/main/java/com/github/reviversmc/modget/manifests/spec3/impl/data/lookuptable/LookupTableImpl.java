package com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable;

import java.util.ArrayList;
import java.util.List;

import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;

public class LookupTableImpl implements LookupTable {
	private ManifestRepository parentRepository;
	private List<LookupTableEntry> lookupTableEntries;

	public LookupTableImpl(ManifestRepository parentRepository) {
		this.parentRepository = parentRepository;

		this.lookupTableEntries = new ArrayList<>(40);
	}

	@Override
	public ManifestRepository getParentRepository() {
		return parentRepository;
	}

	@Override
	public void setParentRepository(ManifestRepository parentRepository) {
		this.parentRepository = parentRepository;
	}


	@Override
	public List<LookupTableEntry> getLookupTableEntries() {
		return lookupTableEntries;
	}

	@Override
	public void setLookupTableEntries(List<LookupTableEntry> lookupTableEntries) {
		this.lookupTableEntries = lookupTableEntries;
	}

}