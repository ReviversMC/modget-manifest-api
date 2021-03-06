package com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.util.LookupTableDownloader;

public class LookupTableImpl implements LookupTable {
	private ManifestRepository parentRepository;
	private List<LookupTableEntry> lookupTableEntries;

	public LookupTableImpl(ManifestRepository parentRepository) {
		this.parentRepository = parentRepository;

		this.lookupTableEntries = new ArrayList<>(40);
	}

	@Override
    @JsonIgnore
	public ManifestRepository getParentRepository() {
		return parentRepository;
	}

	@Override
    @JsonIgnore
	public void setParentRepository(ManifestRepository parentRepository) {
		this.parentRepository = parentRepository;
	}


	@Override
	public List<LookupTableEntry> getEntries() {
		return lookupTableEntries;
	}

	@Override
	public List<LookupTableEntry> getOrDownloadEntries() throws Exception {
        if (lookupTableEntries.isEmpty()) {
            setEntries(LookupTableDownloader.create().downloadLookupTable(parentRepository).getEntries());
        }
		return lookupTableEntries;
	}

	@Override
	public void setEntries(List<LookupTableEntry> lookupTableEntries) {
        if (lookupTableEntries == null) {
            this.lookupTableEntries.clear();
            return;
        }
		this.lookupTableEntries = lookupTableEntries;
	}

}
