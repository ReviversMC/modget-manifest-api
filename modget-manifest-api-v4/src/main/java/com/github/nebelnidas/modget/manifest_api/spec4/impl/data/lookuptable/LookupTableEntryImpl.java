package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable;

import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry;

public class LookupTableEntryImpl implements LookupTableEntry {
	private final LookupTable parentLookupTable;
	private String id;
	private List<String> names;
	private List<String> packages;
	private List<String> tags;

	public LookupTableEntryImpl(@JacksonInject LookupTable parentLookupTable) {
		this.parentLookupTable = parentLookupTable;
	}

	@Override
	public LookupTable getParentLookupTable() {
		return this.parentLookupTable;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public List<String> getNames() {
		return this.names;
	}

	@Override
	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public List<String> getPackages() {
		return this.packages;
	}

	@Override
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	@Override
	public List<String> getTags() {
		return this.tags;
	}

	@Override
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
