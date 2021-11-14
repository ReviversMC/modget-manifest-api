package com.github.nebelnidas.modget.manifest_api.spec3.util;

import java.net.HttpURLConnection;
import java.net.URL;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.ManifestRepository;
import com.github.nebelnidas.modget.manifest_api.spec3.config.ManifestApiV3Config;

public class RepositoryUtils {

	public static RepositoryUtils create() {
		return new RepositoryUtils();
	}


	public boolean doesRepoSupportMajorSpecVersion(ManifestRepository repo, int specVersion) throws Exception {
		String url = String.format("%s/v%s/lookup-table.yaml", repo.getUri(), specVersion);

		HttpURLConnection.setFollowRedirects(false);
		// Note: You may also need HttpURLConnection.setInstanceFollowRedirects(false)
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestMethod("HEAD");

		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return true;
		}
		return false;
	}


	public int getLatestSupportedMajorSpecVersion(ManifestRepository repo) {
		final int MAX_SUPPORTED_VERSION = ManifestApiV3Config.SUPPORTED_MANIFEST_SPECS.get(ManifestApiV3Config.SUPPORTED_MANIFEST_SPECS.size() - 1);
		final int MIN_SUPPORTED_VERSION = ManifestApiV3Config.SUPPORTED_MANIFEST_SPECS.get(0);
		final int MAX_VERSIONS_TO_CHECK = 10;
		int latestSupportedMajorSpecVersion = MIN_SUPPORTED_VERSION;

		for (int versionNumber = MIN_SUPPORTED_VERSION; versionNumber < MAX_SUPPORTED_VERSION + MAX_VERSIONS_TO_CHECK; versionNumber++) {
			try {
				if (doesRepoSupportMajorSpecVersion(repo, versionNumber) == true) {
					latestSupportedMajorSpecVersion = versionNumber;
				}
			} catch (Exception e) {}
		}

		return latestSupportedMajorSpecVersion;
	}


	public boolean checkForNewVersion(ManifestRepository repo) {
		if (getLatestSupportedMajorSpecVersion(repo) > ManifestApiV3Config.SUPPORTED_MANIFEST_SPECS.get(ManifestApiV3Config.SUPPORTED_MANIFEST_SPECS.size() - 1)) {
			return true;
		}
		return false;
	}

}
