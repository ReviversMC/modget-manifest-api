package com.github.nebelnidas.modget.manifest_api.spec3.util;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.ManifestRepository;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.mod.ModPackageImpl;

public class PackageUtils {

	public static PackageUtils create() {
		return new PackageUtils();
	}


	public ModPackage downloadPackage(List<ManifestRepository> repos, String publisher, String id) throws Exception {
		ModPackage pack = new ModPackageImpl(publisher, id);

		for (ManifestRepository repo : repos) {
			for (LookupTableEntry entry : repo.getLookupTable().getLookupTableEntries()) {

				// For each package defined in the lookup table
				for (String packageId : entry.getPackages()) {
					String[] packageIdParts = packageId.split("\\.");

					if (!(packageIdParts[0].equalsIgnoreCase(publisher) && packageIdParts[1].equalsIgnoreCase(id))) {
						continue;
					}

					ModManifest manifest;
					manifest = ManifestUtils.create().downloadManifest(entry, pack);
					if (manifest == null) {continue;}

					pack.addManifest(manifest);
				}
			}
		}
		return pack;
	}

}
