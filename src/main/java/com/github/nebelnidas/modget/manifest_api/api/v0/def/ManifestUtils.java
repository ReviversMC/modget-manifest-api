package com.github.nebelnidas.modget.manifest_api.api.v0.def;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;

public interface ManifestUtils {

	public String assembleManifestUri(Repository repo, String publisher, String modId);

	public Manifest downloadManifest(LookupTableEntry entry, Package pack);

}
