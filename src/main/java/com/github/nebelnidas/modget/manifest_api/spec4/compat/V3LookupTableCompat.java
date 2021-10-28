package com.github.nebelnidas.modget.manifest_api.spec4.compat;

import java.util.ArrayList;
import java.util.List;

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
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.Repository v4Repository
	) throws Exception
	{
		Repository v3Repository = new RepositoryImpl(v4Repository.getId(), v4Repository.getUri());

		LookupTable v3LookupTable = LookupTableUtils.create().downloadLookupTable(v3Repository);

		return convertLookupTable(v3LookupTable, v4Repository);
	}



	public com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable convertLookupTable(
		LookupTable v3LookupTable,
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.Repository v4Repository
	)
	{
		// Create new v4 lookup table
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable v4LookupTable
			= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable.LookupTableImpl(v4Repository);

		// Copy all entries
		List<com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry> v4LookupTableEntries = new ArrayList<>();
		for (LookupTableEntry v3Entry : v3LookupTable.getLookupTableEntries()) {
			com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry v4Entry
				= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable.LookupTableEntryImpl(v4LookupTable);

			v4Entry.setId(v3Entry.getId());
			v4Entry.setNames(v3Entry.getNames());
			v4Entry.setPackages(v3Entry.getPackages());
			v4Entry.setTags(v3Entry.getTags());

			v4LookupTableEntries.add(v4Entry);
		}

		v4LookupTable.setLookupTableEntries(v4LookupTableEntries);

		return v4LookupTable;
	}
}
