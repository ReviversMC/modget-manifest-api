package com.github.nebelnidas.modget.manifest_api.spec3.api.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.RepositoryImpl;

@JsonDeserialize(as = RepositoryImpl.class)
public interface Repository {

	public void init() throws Exception;
	public void refresh() throws Exception;

	public int getId();
	public String getUri();

	public int getMaxSpecVersion();

	public LookupTable getLookupTable();
	public void setLookupTable(LookupTable lookupTable);

	public boolean isEnabled();
	public void setEnabled(boolean enabled);

}
