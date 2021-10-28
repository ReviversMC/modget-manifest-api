package com.github.nebelnidas.modget.test.api.v0.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.RepositoryUtilsImpl;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.RepositoryImpl;

import org.junit.jupiter.api.Test;

public class RepositoryUtilsImplTest {

	@Test
	void assertRepoUpdateCheckWorks() {
		Repository repoWithUpdate = new RepositoryImpl(0, "https://raw.githubusercontent.com/NebelNidas/modget-manifests-with-spec-update-test");
		Repository repoWithoutUpdate = new RepositoryImpl(1, "https://raw.githubusercontent.com/NebelNidas/modget-manifests-without-spec-update-test");

		try {
			assertTrue(RepositoryUtilsImpl.create().checkForNewVersion(repoWithUpdate));
			assertFalse(RepositoryUtilsImpl.create().checkForNewVersion(repoWithoutUpdate));
		} catch (Exception e) {}
	}
}
