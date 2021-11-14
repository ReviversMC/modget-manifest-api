package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;

public class ModPackageImpl implements ModPackage {
	private final String publisher;
	private final String id;
	private List<ModManifest> manifests = new ArrayList<>();


	public ModPackageImpl(String publisher, String id) {
		this.publisher = publisher;
		this.id = id;
	}


	@Override
	public String getPublisher() {
		return this.publisher;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public List<ModManifest> getManifests() {
		return this.manifests;
	}

	@Override
	public void addManifest(ModManifest manifest) {
		this.manifests.add(manifest);
	}

}
