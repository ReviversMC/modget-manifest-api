package com.github.nebelnidas.modget.manifest_api.api.v0.def;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.RecognizedMod;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;

public interface ManifestUtils {

	public String assembleManifestUri(Repository repo, String publisher, String modId);

	public Manifest downloadManifest(Repository repo, String publisher, String modId);

	public List<Package> getAvailablePackages(RecognizedMod mod);

}
