package com.github.nebelnidas.modget.manifest_api.spec3.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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


	public List<Integer> getAvailableManifestSpecMajorVersions(ManifestRepository repo) {
		final int MAX_VERSIONS_TO_CHECK = 10;
		final List<Integer> availableManifestSpecMajorVersions = new ArrayList<>();

		for (int version = 3; version < MAX_VERSIONS_TO_CHECK; version++) {
			try {
				if (doesRepoSupportMajorSpecVersion(repo, version) == true) {
					availableManifestSpecMajorVersions.add(version);
				}
			} catch (Exception e) {}
		}

		return availableManifestSpecMajorVersions;
	}


	public boolean checkForNewVersion(ManifestRepository repo) {
		List<Integer> availableManifestSpecMajorVersions = getAvailableManifestSpecMajorVersions(repo);
		final int MAX_AVAILABLE_VERSION = availableManifestSpecMajorVersions.get(availableManifestSpecMajorVersions.size() - 1);

		if (MAX_AVAILABLE_VERSION > ManifestApiV3Config.SUPPORTED_MANIFEST_SPEC) {
			return true;
		}
		return false;
	}

}
