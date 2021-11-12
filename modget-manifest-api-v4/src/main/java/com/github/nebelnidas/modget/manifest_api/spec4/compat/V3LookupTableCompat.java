package com.github.nebelnidas.modget.manifest_api.spec4.compat;

import java.util.ArrayList;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.RepositoryImpl;
import com.github.nebelnidas.modget.manifest_api.spec3.util.LookupTableUtils;

public class V3LookupTableCompat {

	public static V3LookupTableCompat create() {
		return new V3LookupTableCompat();
	}



	public com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable downloadAndConvertLookupTable(
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.ManifestRepository v4Repository
	) throws Exception
	{
		Repository v3Repository = new RepositoryImpl(v4Repository.getId(), v4Repository.getUri());
		LookupTable v3LookupTable = LookupTableUtils.create().downloadLookupTable(v3Repository);

		return convertLookupTable(v3LookupTable, v4Repository);
	}



	public com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable convertLookupTable(
		LookupTable v3LookupTable,
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.ManifestRepository v4Repository
	)
	{
		// Create new v4 lookup table
		return new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable.LookupTableImpl(v4Repository) {{
			com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable v4LookupTable = this;

			// Set parent repository
			setParentRepository(v4Repository);

			// Copy all entries
			setLookupTableEntries(new ArrayList<com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry>() {{
				for (LookupTableEntry v3Entry : v3LookupTable.getLookupTableEntries()) {
					add(new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable.LookupTableEntryImpl(v4LookupTable) {{
						setId(v3Entry.getId());
						setNames(v3Entry.getNames());
						setTags(v3Entry.getTags());
	
						// Convert packages
						setPackages(new ArrayList<com.github.nebelnidas.modget.manifest_api.spec4.api.data.mod.ModPackage>() {{
							for (String v3PackageId : v3Entry.getPackages()) {
								add(new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.mod.ModPackageImpl(v3PackageId));
							}
						}});
					}});
				}
			}});
		}};
	}

}
