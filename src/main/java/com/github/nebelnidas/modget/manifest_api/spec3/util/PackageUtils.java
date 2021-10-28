package com.github.nebelnidas.modget.manifest_api.spec3.util;

import java.io.IOException;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Package;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.PackageImpl;

public class PackageUtils {

	public static PackageUtils create() {
		return new PackageUtils();
	}


	public Package downloadPackage(List<Repository> repos, String publisher, String id) throws IOException {
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
					try {
						manifest = ManifestUtils.create().downloadManifest(entry, pack);
					} catch (IOException e) {
						throw e;
					}
					if (manifest == null) {continue;}

					pack.addManifest(manifest);
				}
			}
		}
		return pack;
	}

}
