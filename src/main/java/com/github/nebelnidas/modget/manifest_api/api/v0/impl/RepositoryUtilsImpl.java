package com.github.nebelnidas.modget.manifest_api.api.v0.impl;

import java.net.URL;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.config.ApiV0Config;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.RepositoryUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.lookuptable.LookupTableEntryImpl;

public class RepositoryUtilsImpl implements RepositoryUtils {

	@Override
	public boolean checkForNewVersion(Repository repo) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			mapper.readValue(new URL(String.format("%s/v%s/%s", repo.getUri(), ApiV0Config.MAX_SUPPORTED_MANIFEST_SPEC + 1, "lookup-table.yaml")), LookupTableEntryImpl[].class);

			ManifestApi.logInfo("A new repo version has been detected! Please update modget to use it.");
			return true;
        } catch (Exception e) {
			if (e instanceof UnknownHostException) {
				ManifestApi.logWarn("Couldn't check for new repository versions. Please check your Internet connection!", e.getMessage());
				throw e;
			}
        }
		return false;
	}

}
