package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.mod.ModPackage;

public class LookupTableEntryImpl implements LookupTableEntry {
	private LookupTable parentLookupTable;
	private String id;
	private List<String> names;
	private List<String> tags;
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
		this.names = names;
	}


	@Override
	public List<ModPackage> getPackages() {
		return packages;
	}

	@Override
	public void setPackages(List<ModPackage> packages) {
		this.packages = packages;
	}


	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
