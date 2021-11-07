package com.github.nebelnidas.modget.manifest_api.spec4.util;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApiLogger;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.ManifestRepository;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec4.api.exception.VersionNotSupportedException;
import com.github.nebelnidas.modget.manifest_api.spec4.api.util.RepoHandlingUtilsBase;
import com.github.nebelnidas.modget.manifest_api.spec4.compat.V3LookupTableCompat;
import com.github.nebelnidas.modget.manifest_api.spec4.config.ManifestApiV4Config;
import com.github.nebelnidas.modget.manifest_api.spec4.impl.data.lookuptable.LookupTableImpl;

public class LookupTableUtils extends RepoHandlingUtilsBase {

	public static LookupTableUtils create() {
		return new LookupTableUtils();
	}


	public LookupTable downloadLookupTable(ManifestRepository repo) throws Exception {
		final int MAX_AVAILABLE_VERSION = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiV4Config.SUPPORTED_MANIFEST_SPECS,
			repo.getAvailableManifestSpecMajorVersions()
		);


		if (MAX_SHARED_VERSION == -1) {
			throw new VersionNotSupportedException(String.format(
				"This version of the Manifest API doesn't support any of the manifest specifications Repo%s provides!",
				repo.getId()
			), ManifestApiV4Config.SUPPORTED_MANIFEST_SPECS.stream().map(Object::toString).collect(Collectors.toList()),
			repo.getAvailableManifestSpecMajorVersions().stream().map(Object::toString).collect(Collectors.toList()));
		} else if (MAX_AVAILABLE_VERSION < ManifestApiV4Config.MAX_SUPPORTED_VERSION) {
			return V3LookupTableCompat.create().downloadAndConvertLookupTable(repo);
		}



		final LookupTable lookupTable = new LookupTableImpl(repo);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(LookupTable.class, lookupTable);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			List<LookupTableEntry> entries = Arrays.asList(mapper.readValue(new URL(String.format("%s/v%s/lookup-table.yaml", repo.getUri(), MAX_SHARED_VERSION)), LookupTableEntry[].class));

			lookupTable.setLookupTableEntries(entries);
			return lookupTable;
        } catch (Exception e) {
			if (e instanceof UnknownHostException || e instanceof IOException) {
				ManifestApiLogger.logWarn("Couldn't connect to the manifest repository. Please check your Internet connection!", e.getMessage());
			} else {
				ManifestApiLogger.logWarn("Couldn't connect to the manifest repository", e.getMessage());
			}
			throw e;
        }
	}

}
