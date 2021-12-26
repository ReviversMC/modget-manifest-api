package com.github.reviversmc.modget.manifests.spec4.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;

public class ManifestRepositoryUtils {

	public static ManifestRepositoryUtils create() {
		return new ManifestRepositoryUtils();
	}



    /**
     * Checks if the specified repository supports a specific manifest spec version.
     * This is done by trying to access the repo's lookup table, and if the request times out,
     * false is returned.
     */
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



    /**
     * Checks the all manifest specs between the v3 and the currently supported max version
     * and an additional 10 specs upwards if they are supported
     */
    public List<Integer> getAvailableManifestSpecMajorVersions(ManifestRepository repo) {
		final int MAX_VERSIONS_TO_CHECK = 10;
		final List<Integer> availableManifestSpecMajorVersions = new ArrayList<>();

		for (int version = 3; version < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC + MAX_VERSIONS_TO_CHECK; version++) {
			try {
				if (doesRepoSupportMajorSpecVersion(repo, version) == true) {
					availableManifestSpecMajorVersions.add(version);
				}
			} catch (Exception e) {}
		}

		return availableManifestSpecMajorVersions;
	}



    /**
     * Checks if a manifest repository supports newer manifest specs than the currently supported max
     * by this version of the API
     */
    public boolean checkForNewVersion(ManifestRepository repo) {
		List<Integer> availableManifestSpecMajorVersions = getAvailableManifestSpecMajorVersions(repo);
		final int MAX_AVAILABLE_VERSION = availableManifestSpecMajorVersions.get(availableManifestSpecMajorVersions.size() - 1);

		if (MAX_AVAILABLE_VERSION > ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
			return true;
		}
		return false;
	}

}
