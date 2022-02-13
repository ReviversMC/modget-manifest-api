package com.github.reviversmc.modget.manifests.spec4.impl.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;

public class BasicManifestRepositoryUtils {

	public static BasicManifestRepositoryUtils create() {
		return new BasicManifestRepositoryUtils();
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
     * and a additional X specs upwards if they are supported
     */
    public List<Integer> getAvailableManifestSpecMajorVersions(ManifestRepository repo, int maxVersionsToCheck) {
		List<Integer> availableManifestSpecMajorVersions = new ArrayList<>();

		for (int version = 3; version < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC + maxVersionsToCheck; version++) {
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
    public boolean checkForNewVersion(ManifestRepository repo, int maxVersionsToCheck) {
		List<Integer> availableManifestSpecMajorVersions = getAvailableManifestSpecMajorVersions(repo, maxVersionsToCheck);
		int maxAvailableVersion = availableManifestSpecMajorVersions.get(availableManifestSpecMajorVersions.size() - 1);

		if (maxAvailableVersion > ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
			return true;
		}
		return false;
	}

}
