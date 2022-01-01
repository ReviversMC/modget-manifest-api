package com.github.reviversmc.modget.manifests.spec4;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.reviversmc.modget.manifests.TestConfig;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.impl.data.BasicManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.impl.downloaders.BasicModManifestDownloader;
import com.github.reviversmc.modget.manifests.spec4.impl.downloaders.BasicModVersionDownloader;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;

public class Main {
	private ManifestRepository repo = new BasicManifestRepository(0, TestConfig.specV4RepoUrl);

	@Test
	private void assertEverythingWorks() {
		try {
            repo.init();
			LookupTable lookupTable = repo.getOrDownloadLookupTable();

			for (LookupTableEntry entry : lookupTable.getEntries()) {
				for (ModPackage modPackage : entry.getPackages()) {
					ModManifest modManifest = BasicModManifestDownloader.create().downloadModManifest(entry, modPackage);

					for (ModVersion modVersion : modManifest.getVersions()) {
						modVersion = BasicModVersionDownloader.create().downloadModVersion(modManifest, modVersion.getVersion());

						for (ModVersionVariant modVersionVariant : modVersion.getVariants()) {
							if (modVersionVariant.getMinecraftVersions().contains("1.17.1")) {
								System.out.println(String.format("Package %s supports 1.17.1!", modPackage.getPackageId()));
							}
						}
					}
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}



		try {
			assertTrue(repo.getOrDownloadLookupTable().getEntries().get(0).getId() != "");
		} catch (Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}
}
