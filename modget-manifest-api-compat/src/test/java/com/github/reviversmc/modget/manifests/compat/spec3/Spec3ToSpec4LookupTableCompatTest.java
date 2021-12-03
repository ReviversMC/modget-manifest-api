package com.github.reviversmc.modget.manifests.compat.spec3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.reviversmc.modget.manifests.TestConfig;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.impl.data.ManifestRepositoryImpl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;

public class Spec3ToSpec4LookupTableCompatTest {
	private ManifestRepository repo = new ManifestRepositoryImpl(0, TestConfig.specV3RepoUrl);

	@Test
	private void assertConversionWorks() {
        try {
            repo.init();
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
