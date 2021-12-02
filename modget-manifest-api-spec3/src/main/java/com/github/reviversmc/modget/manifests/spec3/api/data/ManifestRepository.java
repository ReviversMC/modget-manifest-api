package com.github.reviversmc.modget.manifests.spec3.api.data;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec3.impl.data.ManifestRepositoryImpl;

@JsonDeserialize(as = ManifestRepositoryImpl.class)
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

	public boolean isEnabled();
	public void setEnabled(boolean enabled);

}
