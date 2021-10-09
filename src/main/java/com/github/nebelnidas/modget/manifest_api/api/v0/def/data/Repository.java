package com.github.nebelnidas.modget.manifest_api.api.v0.def.data;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTable;

public interface Repository {

	public void refresh() throws Exception;

	public LookupTable downloadLookupTable() throws Exception;

	public int getId();
	public String getUri();
	public String getUriWithSpec();

	public LookupTable getLookupTable();
	public void setLookupTable(LookupTable lookupTable);

	public boolean isEnabled();
	public void setEnabled(boolean enabled);

}
