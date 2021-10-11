package com.github.nebelnidas.modget.manifest_api.api.v0.def.data;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.RecognizedModImpl;

@JsonDeserialize(as = RecognizedModImpl.class)
public interface RecognizedMod {

	public String getId();
	public void setId(String id);

	public String getCurrentVersion();
	public void setCurrentVersion(String currentVersion);

	public List<LookupTableEntry> getLookupTableEntries();
	public void addLookupTableEntry(LookupTableEntry lookupTableEntry);

	public List<Package> getAvailablePackages();
	public void addAvailablePackage(Package availablePackage);

	public boolean isUpdateAvailable();
	public void setUpdateAvailable(boolean updateAvailable);

}
