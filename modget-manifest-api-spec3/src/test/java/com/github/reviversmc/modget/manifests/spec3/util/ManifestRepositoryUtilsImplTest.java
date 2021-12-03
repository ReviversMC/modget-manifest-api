package com.github.reviversmc.modget.manifests.spec3.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.reviversmc.modget.manifests.TestConfig;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.impl.data.ManifestRepositoryImpl;

import org.junit.jupiter.api.Test;

public class ManifestRepositoryUtilsImplTest {

	@Test
	void assertV3RepoUpdateCheckWorks() {
		ManifestRepositoryUtils utils = new ManifestRepositoryUtils();

		ManifestRepository repoWithUpdate = new ManifestRepositoryImpl(0, TestConfig.specV4RepoUrl);
		ManifestRepository repoWithoutUpdate = new ManifestRepositoryImpl(1, TestConfig.specV3RepoUrl);

		try {
            repoWithUpdate.init();
			assertTrue(utils.checkForNewVersion(repoWithUpdate));
            repoWithoutUpdate.init();
			assertFalse(utils.checkForNewVersion(repoWithoutUpdate));
		} catch (Exception e) {}
	}
}
