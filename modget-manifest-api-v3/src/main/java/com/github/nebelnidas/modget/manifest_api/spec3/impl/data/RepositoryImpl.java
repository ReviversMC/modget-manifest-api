package com.github.nebelnidas.modget.manifest_api.spec3.impl.data;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec3.util.LookupTableUtils;
import com.github.nebelnidas.modget.manifest_api.spec3.util.RepositoryUtils;

public class RepositoryImpl implements Repository {
	private final int ID;
	private final String URI;
	private final int MAX_SPEC_VERSION;
	private LookupTable lookupTable;
	private boolean enabled = true;

	public RepositoryImpl(int id, String uri) {
		this.ID = id;
		if (uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		this.URI = uri;
		this.MAX_SPEC_VERSION = RepositoryUtils.create().getLatestSupportedMajorSpecVersion(this);
	}

	@Override
	public void init() throws Exception {
		refresh();
	}

	@Override
	public void refresh() throws Exception {
		lookupTable = LookupTableUtils.create().downloadLookupTable(this);
	}



	@Override
	public int getId() {
		return this.ID;
	}

	@Override
	public String getUri() {
		return this.URI;
	}

	@Override
	public int getMaxSpecVersion() {
		return this.MAX_SPEC_VERSION;
	}

	@Override
	public LookupTable getLookupTable() {
		return this.lookupTable;
	}

	@Override
	public void setLookupTable(LookupTable lookupTable) {
		this.lookupTable = lookupTable;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
