package com.github.nebelnidas.modget.manifest_api.api.v0.impl;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.PackageUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.PackageImpl;

public class PackageUtilsImpl implements PackageUtils {

	public static PackageUtilsImpl create() {
		return new PackageUtilsImpl();
	}


	@Override
	public Package downloadPackage(List<Repository> repos, String publisher, String id) {
		Package pack = new PackageImpl(publisher, id);

		for (Repository repo : repos) {
			for (LookupTableEntry entry : repo.getLookupTable().getLookupTableEntries()) {

				// For each package defined in the lookup table
				for (String packageId : entry.getPackages()) {
					String[] packageIdParts = packageId.split("\\.");

					if (!(packageIdParts[0].equalsIgnoreCase(publisher) && packageIdParts[1].equalsIgnoreCase(id))) {
						continue;
					}

					try {
						Manifest manifest = ManifestApi.MANIFEST_UTILS.downloadManifest(entry, pack);
						if (manifest == null) {continue;}

						pack.addManifest(manifest);

					} catch (Exception e) {
						ManifestApi.logWarn(String.format("An error occurred while parsing the Repo%s.%s.%s manifest", repo.getId(), packageIdParts[0], packageIdParts[1]), e.getMessage());
					}
				}
			}
		}
		return pack;
	}

}
