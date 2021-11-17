package com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;

public interface LookupTable {

	public ManifestRepository getParentRepository();
	public void setParentRepository(ManifestRepository parentRepository);

	public List<LookupTableEntry> getLookupTableEntries();
	public void setLookupTableEntries(List<LookupTableEntry> lookupTableEntries);

}
