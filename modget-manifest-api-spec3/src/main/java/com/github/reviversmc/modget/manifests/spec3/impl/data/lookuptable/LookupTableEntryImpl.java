package com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.deserialization.PackageIdToModPackageConverter;
import com.github.reviversmc.modget.manifests.spec3.util.LookupTableDownloader;

public class LookupTableEntryImpl implements LookupTableEntry {
	private LookupTable parentLookupTable;
	private String id;
	private List<String> tags;
	private List<String> names;
    @JsonDeserialize(converter = PackageIdToModPackageConverter.class)
	private List<ModPackage> packages;

	public LookupTableEntryImpl(@JacksonInject LookupTable parentLookupTable) {
		this.parentLookupTable = parentLookupTable;

		this.names = new ArrayList<>(4);
		this.tags = new ArrayList<>(5);
		this.packages = new ArrayList<>(2);
	}


	@Override
	public LookupTable getParentLookupTable() {
		return parentLookupTable;
	}

	@Override
	public void setParentLookupTable(LookupTable parentLookupTable) {
		this.parentLookupTable = parentLookupTable;
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public List<String> getNames() {
		return names;
	}

	@Override
	public void setNames(List<String> names) {
        if (names == null) {
            this.names.clear();
            return;
        }
		this.names = names;
	}


	@Override
	public List<ModPackage> getPackages() {
		return packages;
	}

	@Override
	public List<ModPackage> getOrDownloadPackages() throws Exception {
        if (packages.isEmpty()) {
            for (LookupTableEntry entry : LookupTableDownloader.create().downloadLookupTable(parentLookupTable.getParentRepository()).getEntries()) {
                if (entry.getId().equals(this.getId())) {
                    setPackages(entry.getPackages());
                    break;
                }
            }
        }
		return packages;
	}

	@Override
	public void setPackages(List<ModPackage> packages) {
        if (packages == null) {
            this.packages.clear();
            return;
        }
		this.packages = packages;
	}


	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<String> tags) {
        if (tags == null) {
            this.tags.clear();
            return;
        }
		this.tags = tags;
	}

}
