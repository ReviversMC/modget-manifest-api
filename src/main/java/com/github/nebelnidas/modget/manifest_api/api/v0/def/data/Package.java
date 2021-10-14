package com.github.nebelnidas.modget.manifest_api.api.v0.def.data;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;

public interface Package {

	public String getPublisher();

	public String getId();

	public List<Manifest> getManifests();
	public void addManifest(Manifest manifest);

}
