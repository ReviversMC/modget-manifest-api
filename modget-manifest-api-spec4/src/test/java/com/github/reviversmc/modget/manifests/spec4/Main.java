package com.github.reviversmc.modget.manifests.spec4;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.reviversmc.modget.manifests.ManifestApiLogger;
import com.github.reviversmc.modget.manifests.TestConfig;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.data.ManifestRepositoryImpl;
import com.github.reviversmc.modget.manifests.spec4.util.LookupTableUtils;
import com.github.reviversmc.modget.manifests.spec4.util.ManifestUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;

public class Main {
	private ManifestRepository repo = new ManifestRepositoryImpl(0, TestConfig.specV4RepoUrl);

	@Test
	private void assertEverythingWorks() {
		LookupTable lookupTable;
		try {
			lookupTable = LookupTableUtils.create().downloadLookupTable(repo);
			ManifestApiLogger.logInfo("LookupTable downloaded!");

			for (LookupTableEntry entry : lookupTable.getLookupTableEntries()) {{
				for (ModPackage modPackage : entry.getPackages()) {
					ModManifest manifest = ManifestUtils.create().downloadManifest(entry, modPackage);
					ManifestApiLogger.logInfo("ModManifest downloaded! ID: " + modPackage.getPackageId());
				}
			}}


		} catch (Exception e) {
			e.printStackTrace();
		}



		try {
			assertTrue(LookupTableUtils.create().downloadLookupTable(repo).getLookupTableEntries().get(0).getId() != "");
		} catch (Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}
}
