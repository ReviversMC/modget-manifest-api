package com.github.nebelnidas.modget.manifest_api.spec3.api.data;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ModVersion;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.RecognizedModImpl;

@JsonDeserialize(as = RecognizedModImpl.class)
public interface RecognizedMod {

	public String getId();

	public String getCurrentVersion();

	public List<Package> getAvailablePackages();
	public void addAvailablePackage(Package availablePackage);

	public List<ModVersion> getUpdates();
	public void addUpdate(ModVersion update);
	public void resetUpdates();

}
