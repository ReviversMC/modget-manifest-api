package com.github.nebelnidas.modget.manifest_api.spec3.api.data;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.Manifest;

public interface Package {

	public String getPublisher();

	public String getId();

	public List<Manifest> getManifests();
	public void addManifest(Manifest manifest);

}
