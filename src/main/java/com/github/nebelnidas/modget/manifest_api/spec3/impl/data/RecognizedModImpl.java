package com.github.nebelnidas.modget.manifest_api.spec3.impl.data;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Package;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.RecognizedMod;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ModVersion;

public class RecognizedModImpl implements RecognizedMod {
	private final String id;
	private String currentVersion;
	private List<Package> availablePackages = new ArrayList<>(2);
	private List<ModVersion> updates = new ArrayList<>(2);


	public RecognizedModImpl(String id) {
		this.id = id;
	}

	public RecognizedModImpl(String id, String currentVersion) {
		this.id = id;
		this.currentVersion = currentVersion;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getCurrentVersion() {
		return this.currentVersion;
	}

	@Override
	public List<Package> getAvailablePackages() {
		return this.availablePackages;
	}

	@Override
	public void addAvailablePackage(Package availablePackage) {
		this.availablePackages.add(availablePackage);
	}

	@Override
	public List<ModVersion> getUpdates() {
		return this.updates;
	}

	@Override
	public void addUpdate(ModVersion update) {
		updates.add(update);
	}

	@Override
	public void resetUpdates() {
		updates.clear();
	}

}
