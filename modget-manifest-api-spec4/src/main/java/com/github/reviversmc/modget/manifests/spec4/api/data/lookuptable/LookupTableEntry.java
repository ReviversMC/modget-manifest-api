package com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable;

import java.util.List;

import javax.annotation.Nonnull;

import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;

public interface LookupTableEntry {

	public LookupTable getParentLookupTable();
	public void setParentLookupTable(LookupTable parentLookupTable);

    /**
	 * Gets the mod ID of the current entry
	 */
	public String getId();
    /**
	 * Sets the mod ID of the current entry
	 */
	public void setId(String id);

    /**
	 * Gets all names of this mod that are not equal to the mod's ID. Cannot return null.
	 */
    @Nonnull
	public List<String> getAlternativeNames();
    /**
	 * Sets all names of this mod that are not equal to the mod's ID.
     * You can try to set it to null, but it will instead just clear the current list.
	 */
	public void setAlternativeNames(List<String> names);

    /**
	 * Gets all tags associated with the current mod. Cannot return null.
	 */
    @Nonnull
	public List<String> getTags();
    /**
	 * Sets the mod's tags. You can try to set it to null, but it will instead just clear the current list.
	 */
	public void setTags(List<String> tags);

    /**
	 * Gets all available packages for this mod. Cannot return null.
	 */
    @Nonnull
	public List<ModPackage> getPackages();
    /**
	 * Gets all packages of this lookup table if downloaded, otherwise downloads them.
     * Cannot return null.
	 */
    @Nonnull
	public List<ModPackage> getOrDownloadPackages() throws Exception;
    /**
	 * Sets the mod's available packages.
     * You can try to set it to null, but it will instead just clear the current list.
	 */
	public void setPackages(List<ModPackage> packages);

}
