package com.github.nebelnidas.modget.manifest_api.api.v0.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.config.ApiV0Config;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.RepositoryUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;

public class RepositoryUtilsImpl implements RepositoryUtils {

	public static RepositoryUtilsImpl create() {
		return new RepositoryUtilsImpl();
	}


	@Override
	public boolean checkForNewVersion(Repository repo) throws Exception {
		String url = String.format("%s/v%s/%s", repo.getUri(), ApiV0Config.MAX_SUPPORTED_MANIFEST_SPEC + 1, "lookup-table.yaml");

		try {
			HttpURLConnection.setFollowRedirects(false);
			// Note: You may also need HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("HEAD");

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ManifestApi.logInfo("A new repo version has been detected! Please update modget to use it.");
				return true;
			}
		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				ManifestApi.logWarn("Couldn't check for new repository versions. Please check your Internet connection!", e.getMessage());
				throw e;
			}
		}
		return false;
	}

}
