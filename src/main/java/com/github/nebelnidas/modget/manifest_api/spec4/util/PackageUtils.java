package com.github.nebelnidas.modget.manifest_api.spec4.util;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec4.api.data.Package;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.PackageImpl;

public class PackageUtils {

	public static PackageUtils create() {
		return new PackageUtils();
	}


	public Package downloadPackage(List<Repository> repos, String publisher, String id) throws Exception {
		Package pack = new PackageImpl(publisher, id);

		for (Repository repo : repos) {
			for (LookupTableEntry entry : repo.getLookupTable().getLookupTableEntries()) {

				// For each package defined in the lookup table
				for (String packageId : entry.getPackages()) {
					String[] packageIdParts = packageId.split("\\.");

					if (!(packageIdParts[0].equalsIgnoreCase(publisher) && packageIdParts[1].equalsIgnoreCase(id))) {
						continue;
					}

					Manifest manifest;
					manifest = ManifestUtils.create().downloadManifest(entry, pack);
					if (manifest == null) {continue;}

					pack.addManifest(manifest);
				}
			}
		}
		return pack;
	}

}
