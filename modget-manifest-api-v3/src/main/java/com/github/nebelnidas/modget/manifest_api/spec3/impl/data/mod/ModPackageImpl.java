package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;

public class ModPackageImpl implements ModPackage {
	private String packageId;
	private String publisher;
	private String modId;
	private List<ModManifest> manifests;


	public ModPackageImpl(String packageId) {
		this.packageId = packageId;
		manifests = new ArrayList<>(1);
	}

	public ModPackageImpl(String modId, String publisher) {
		this.modId = modId;
		this.publisher = publisher;
		manifests = new ArrayList<>(1);
	}


	@Override
	public String getPackageId() {
		return packageId;
	}

	@Override
	public void setPackageId(String packageId) {
		this.packageId = packageId;
		String[] packageIdParts = packageId.split("\\.");
		publisher = packageIdParts[0];
		modId = packageIdParts[1];
	}


	@Override
	public String getPublisher() {
		return publisher;
	}

	@Override
	public void setPublisher(String publisher) {
		this.publisher = publisher;
		packageId = String.format("%s.%s", publisher, modId);
	}


	@Override
	public String getModId() {
		return modId;
	}

	@Override
	public void setModId(String modId) {
		this.modId = modId;
		packageId = String.format("%s.%s", publisher, modId);
	}


	@Override
	public List<ModManifest> getManifests() {
		return manifests;
	}

	@Override
	public void addManifest(ModManifest manifest) {
		manifests.add(manifest);
	}

	@Override
	public void setManifests(List<ModManifest> manifests) {
		this.manifests = manifests;
	}

}
