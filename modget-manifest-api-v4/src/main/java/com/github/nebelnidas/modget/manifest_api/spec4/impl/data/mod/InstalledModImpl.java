package com.github.nebelnidas.modget.manifest_api.spec4.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec4.api.data.mod.InstalledMod;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.mod.ModPackage;

public class InstalledModImpl implements InstalledMod {
	private String id;
	private String installedVersion;
	private List<ModPackage> availablePackages;


	public InstalledModImpl(String id) {
		this.id = id;

		this.availablePackages = new ArrayList<>(2);
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String getInstalledVersion() {
		return installedVersion;
	}

	@Override
	public void setInstalledVersion(String installedVersion) {
		this.installedVersion = installedVersion;
	}


	@Override
	public List<ModPackage> getAvailablePackages() {
		return availablePackages;
	}

	@Override
	public void addAvailablePackage(ModPackage availablePackage) {
		availablePackages.add(availablePackage);
	}

	@Override
	public void setAvailablePackages(List<ModPackage> availablePackages) {
		this.availablePackages = availablePackages;
	}

}
