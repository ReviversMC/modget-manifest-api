package com.github.reviversmc.modget.manifests.spec4.impl.data;

import java.util.ArrayList;
import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec4.impl.downloaders.BasicLookupTableDownloader;
import com.github.reviversmc.modget.manifests.spec4.impl.util.BasicManifestRepositoryUtils;

public class BasicManifestRepository implements ManifestRepository {
	private int id;
	private String uri;
	private List<Integer> availableManifestSpecMajorVersions;
	private LookupTable lookupTable;

	public BasicManifestRepository(int id, String uri) {
		this.id = id;
		setUri(uri);

        availableManifestSpecMajorVersions = new ArrayList<>(2);
	}

	@Override
	public void init() throws Exception {
		refresh();
	}

	@Override
	public void refresh() throws Exception {
		setSupportedManifestSpecMajorVersions(BasicManifestRepositoryUtils.create().getAvailableManifestSpecMajorVersions(this, 2));
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
        if (availableManifestSpecMajorVersions == null) {
            this.availableManifestSpecMajorVersions.clear();
            return;
        }
		this.availableManifestSpecMajorVersions = availableManifestSpecMajorVersions;
	}


	@Override
	public LookupTable getLookupTable() {
		return lookupTable;
	}

	@Override
	public LookupTable getOrDownloadLookupTable() throws Exception {
        if (lookupTable == null) {
            setLookupTable(BasicLookupTableDownloader.create().downloadLookupTable(this));
        }
		return lookupTable;
	}

	@Override
	public void setLookupTable(LookupTable lookupTable) {
		this.lookupTable = lookupTable;
	}

}
