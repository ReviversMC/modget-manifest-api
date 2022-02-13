package com.github.reviversmc.modget.manifests.spec4.impl.downloaders;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.reviversmc.modget.manifests.ManifestApiLogger;
import com.github.reviversmc.modget.manifests.config.ManifestApiConfig;
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTable;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;
import com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.BasicLookupTable;
import com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.BasicLookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.impl.data.mod.BasicModPackage;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class BasicLookupTableDownloader extends RepoHandlingUtilsBase {

	public static BasicLookupTableDownloader create() {
		return new BasicLookupTableDownloader();
	}



    /**
     * Downloads the lookup table from the specified manifest repository
     */
    public BasicLookupTable downloadLookupTable(ManifestRepository repo) throws Exception {
		int maxAvailableVersion = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		int maxSharedVersion = findMaxSharedInt(
			ManifestApiConfig.KNOWN_MANIFEST_SPECS,
			repo.getAvailableManifestSpecMajorVersions()
		);


		boolean notSupported = false;
		if (maxSharedVersion == -1) {
			notSupported = true;
		} else if (maxAvailableVersion < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
            notSupported = true;
		}

		if (notSupported) {
			List<String> versions;
            versions = ManifestApiConfig.KNOWN_MANIFEST_SPECS.stream().map(Object::toString).collect(Collectors.toList());
			throw new VersionNotSupportedException(String.format(
				"This version of the Manifest API doesn't support any of the manifest specifications provided by Repo%s!",
				repo.getId()
			), versions,
			repo.getAvailableManifestSpecMajorVersions().stream().map(Object::toString).collect(Collectors.toList()));
		}


		YAMLMapper mapper = new YAMLMapper().builder()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
			.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
			.build();
        // Deserialize interfaces with correct implementations
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(ModPackage.class, BasicModPackage.class);
        mapper.registerModule(simpleModule);
        // Inject correct parent objects
		InjectableValues.Std injectableValues = new InjectableValues.Std();
		BasicLookupTable lookupTable = new BasicLookupTable(repo);
        injectableValues.addValue(LookupTable.class, lookupTable);
        mapper.setInjectableValues(injectableValues);

		try {
			List<LookupTableEntry> entries = Arrays.asList(mapper.readValue(new URL(String.format("%s/v%s/lookup-table.yaml", repo.getUri(), maxSharedVersion)), BasicLookupTableEntry[].class));

			lookupTable.setEntries(entries);

            ManifestApiLogger.logInfo(String.format("Fetched lookup table from Repo%s", repo.getId()));
			return lookupTable;
        } catch (Exception e) {
			if (e instanceof UnknownHostException) {
				ManifestApiLogger.logWarn("Couldn't connect to the manifest repository. Please check your Internet connection!", ExceptionUtils.getStackTrace(e));
			} else {
				ManifestApiLogger.logWarn("Couldn't connect to the manifest repository", ExceptionUtils.getStackTrace(e));
			}
			throw e;
        }
	}

}
