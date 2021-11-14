package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModVersion;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.InstalledMod;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;

public class InstalledModImpl implements InstalledMod {
	private final String id;
	private String currentVersion;
	private List<ModPackage> availablePackages = new ArrayList<>(2);
	private List<ModVersion> updates = new ArrayList<>(2);


	public InstalledModImpl(String id) {
		this.id = id;
	}

	public InstalledModImpl(String id, String currentVersion) {
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
	public List<ModPackage> getAvailablePackages() {
		return this.availablePackages;
	}

	@Override
	public void addAvailablePackage(ModPackage availablePackage) {
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
