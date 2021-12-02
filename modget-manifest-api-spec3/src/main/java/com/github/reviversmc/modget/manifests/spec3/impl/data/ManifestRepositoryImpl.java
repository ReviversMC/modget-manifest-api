package com.github.reviversmc.modget.manifests.spec3.impl.data;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec3.util.LookupTableDownloader;
import com.github.reviversmc.modget.manifests.spec3.util.ManifestRepositoryUtils;

public class ManifestRepositoryImpl implements ManifestRepository {
	private int id;
	private String uri;
	private List<Integer> availableManifestSpecMajorVersions;
	private LookupTable lookupTable;
	private boolean enabled = true;

	public ManifestRepositoryImpl(int id, String uri) {
		this.id = id;
		setUri(uri);
		this.availableManifestSpecMajorVersions = ManifestRepositoryUtils.create().getAvailableManifestSpecMajorVersions(this);
	}

	@Override
	public void init() throws Exception {
		refresh();
	}

	@Override
	public void refresh() throws Exception {
		lookupTable = LookupTableDownloader.create().downloadLookupTable(this);
	}


	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String getUri() {
		return uri;
	}

	@Override
	public void setUri(String uri) {
		if (uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		this.uri = uri;
	}


	@Override
	public List<Integer> getAvailableManifestSpecMajorVersions() {
		return availableManifestSpecMajorVersions;
	}

	@Override
	public void setSupportedManifestSpecMajorVersions(List<Integer> availableManifestSpecMajorVersions) {
		this.availableManifestSpecMajorVersions = availableManifestSpecMajorVersions;
	}


	@Override
	public LookupTable getLookupTable() {
		return lookupTable;
	}

	@Override
	public void setLookupTable(LookupTable lookupTable) {
		this.lookupTable = lookupTable;
	}


	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
