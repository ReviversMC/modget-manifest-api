package com.github.nebelnidas.modget.manifest_api.spec4.compat;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.nebelnidas.modget.manifest_api.TestConfig;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.Repository;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.RepositoryImpl;
import com.github.nebelnidas.modget.manifest_api.spec4.util.LookupTableUtils;

import org.junit.jupiter.api.Test;

public class V3LookupTableCompatTest {
	private Repository repo = new RepositoryImpl(0, TestConfig.specV3RepoUrl);

	@Test
	private void assertConversionWorks() {
		try {
			assertTrue(LookupTableUtils.create().downloadLookupTable(repo).getLookupTableEntries().get(0).getId() != "");
		} catch (Exception e) {
			fail();
		}
	}
}
