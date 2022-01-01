package com.github.reviversmc.modget.manifests.compat.spec3;

import java.util.ArrayList;

import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.ManifestRepositoryImpl;

public class Spec3ToSpec4LookupTableCompat {

	public static Spec3ToSpec4LookupTableCompat create() {
		return new Spec3ToSpec4LookupTableCompat();
	}



	public com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable downloadAndConvertLookupTable(
		com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository v4Repository
	) throws Exception
	{
		ManifestRepository v3Repository = new ManifestRepositoryImpl(v4Repository.getId(), v4Repository.getUri());
        v3Repository.init();
		LookupTable v3LookupTable = v3Repository.getOrDownloadLookupTable();

		return convertLookupTable(v3LookupTable, v4Repository);
	}



	public com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable convertLookupTable(
		LookupTable v3LookupTable,
		com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository v4Repository
	)
	{
		// Create new v4 lookup table
		return new com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.BasicLookupTable(v4Repository) {{
			com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable v4LookupTable = this;

			// Set parent repository
			setParentRepository(v4Repository);

			// Copy all entries
			setEntries(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry>() {{
				for (LookupTableEntry v3Entry : v3LookupTable.getEntries()) {
					add(new com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.BasicLookupTableEntry(v4LookupTable) {{
						setId(v3Entry.getId());
						setAlternativeNames(v3Entry.getNames());
						setTags(v3Entry.getTags());

						// Convert packages
						setPackages(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage>() {{
							for (ModPackage v3ModPackage : v3Entry.getPackages()) {
								add(new com.github.reviversmc.modget.manifests.spec4.impl.data.mod.BasicModPackage(v3ModPackage.getPackageId()));
							}
						}});
					}});
				}
			}});
		}};
	}

}
