package com.github.nebelnidas.modget.manifest_api.spec3.impl.data;

import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Package;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.Manifest;

public class PackageImpl implements Package {
	private final String publisher;
	private final String id;
	private List<Manifest> manifests = new ArrayList<>();


	public PackageImpl(String publisher, String id) {
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
	public List<Manifest> getManifests() {
		return this.manifests;
	}

	@Override
	public void addManifest(Manifest manifest) {
		this.manifests.add(manifest);
	}

}
