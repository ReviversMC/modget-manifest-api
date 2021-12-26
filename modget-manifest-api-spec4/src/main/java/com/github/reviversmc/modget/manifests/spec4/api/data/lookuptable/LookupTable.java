package com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable;

import java.util.List;

import javax.annotation.Nonnull;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;

public interface LookupTable {

    /**
	 * Gets the parent manifest repository
	 */
	public ManifestRepository getParentRepository();
    /**
	 * Sets the parent repository
	 */
	public void setParentRepository(ManifestRepository parentRepository);

    /**
	 * Gets all entries of this lookup table.
     * Cannot return null.
	 */
    @Nonnull
	public List<LookupTableEntry> getEntries();
    /**
	 * Gets all entries of this lookup table if downloaded, otherwise downloads them.
     * Cannot return null.
	 */
    @Nonnull
	public List<LookupTableEntry> getOrDownloadEntries() throws Exception;
    /**
	 * Overrides the already set lookup table entries.
	 */
	public void setEntries(List<LookupTableEntry> lookupTableEntries);

}
