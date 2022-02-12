package com.github.reviversmc.modget.manifests.spec4.api.data;

import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;

public interface ManifestRepository {

	public void init() throws Exception;
	public void refresh() throws Exception;

	public int getId();
	public void setId(int id);

	public String getUri();
	public void setUri(String uri);

	public List<Integer> getAvailableManifestSpecMajorVersions();
	public void setSupportedManifestSpecMajorVersions(List<Integer> availableManifestSpecMajorVersions);

	public LookupTable getLookupTable();
	public LookupTable getOrDownloadLookupTable() throws Exception;
	public void setLookupTable(LookupTable lookupTable);

}
