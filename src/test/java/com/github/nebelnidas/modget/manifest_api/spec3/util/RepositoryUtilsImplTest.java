package com.github.nebelnidas.modget.manifest_api.spec3.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.RepositoryImpl;

import org.junit.jupiter.api.Test;

public class RepositoryUtilsImplTest {
	private final String specV3RepoUrl = "https://raw.githubusercontent.com/NebelNidas/modget-manifests-test-spec-v3";
	private final String specV4RepoUrl = "https://raw.githubusercontent.com/NebelNidas/modget-manifests-test-spec-v4";

	@Test
	void assertV3RepoUpdateCheckWorks() {
		RepositoryUtils utils = new RepositoryUtils();

		Repository repoWithUpdate = new RepositoryImpl(0, specV4RepoUrl);
		Repository repoWithoutUpdate = new RepositoryImpl(1, specV3RepoUrl);

		try {
			assertTrue(utils.checkForNewVersion(repoWithUpdate));
			assertFalse(utils.checkForNewVersion(repoWithoutUpdate));
		} catch (Exception e) {}
	}
}
