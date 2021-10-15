package com.github.nebelnidas.modget.manifest_api.api.v0.def.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.RepositoryImpl;

@JsonDeserialize(as = RepositoryImpl.class)
public interface Repository {

	public void init() throws Exception;
	public void refresh() throws Exception;

	public int getId();
	public String getUri();
	public String getUriWithSpec();

	public LookupTable getLookupTable();
	public void setLookupTable(LookupTable lookupTable);

	public boolean isEnabled();
	public void setEnabled(boolean enabled);

}
