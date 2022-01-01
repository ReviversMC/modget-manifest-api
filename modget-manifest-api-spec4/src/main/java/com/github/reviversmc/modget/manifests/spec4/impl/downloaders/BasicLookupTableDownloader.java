package com.github.reviversmc.modget.manifests.spec4.impl.downloaders;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
    public LookupTable downloadLookupTable(ManifestRepository repo) throws Exception {
		final int MAX_AVAILABLE_VERSION = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiConfig.KNOWN_MANIFEST_SPECS,
			repo.getAvailableManifestSpecMajorVersions()
		);

		final String backCompatClassPath = "com.github.reviversmc.modget.manifests.compat.spec3.Spec3ToSpec4LookupTableCompat";
		boolean backCompatModuleAvailable;
		try {
			Class.forName(backCompatClassPath);
			backCompatModuleAvailable = true;
		} catch(ClassNotFoundException e) {
			backCompatModuleAvailable = false;
		}


		boolean notSupported = false;
		if (MAX_SHARED_VERSION == -1) {
			notSupported = true;
		} else if (MAX_AVAILABLE_VERSION < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
			if (backCompatModuleAvailable == false) {
				notSupported = true;
			} else {
				ManifestApiLogger.logInfo("Utilizing back-compat module...");

				String convertMethodName = "downloadAndConvertLookupTable";
				Class<?>[] formalParameters = { ManifestRepository.class };
				Object[] effectiveParameters = new Object[] { repo };

				try {
					Class<?> Spec3ToSpec4LookupTableCompat = Class.forName(backCompatClassPath);
					Method method = Spec3ToSpec4LookupTableCompat.getMethod(convertMethodName, formalParameters);
					Object newInstance = Spec3ToSpec4LookupTableCompat.getDeclaredConstructor().newInstance();
					Object value = method.invoke(newInstance, effectiveParameters);

					return (LookupTable)value;

				} catch (Exception e) {
					ManifestApiLogger.logWarn("Back-compat module has failed!", ExceptionUtils.getStackTrace(e));
					notSupported = true;
				}
			}
		}

		if (notSupported) {
			List<String> versions;
			if (backCompatModuleAvailable == true) {
				versions = ManifestApiConfig.KNOWN_MANIFEST_SPECS.stream().map(Object::toString).collect(Collectors.toList());
			} else {
				versions = Arrays.asList(Integer.toString(ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC));
			}
			throw new VersionNotSupportedException(String.format(
				"This version of the Manifest API doesn't support any of the manifest specifications provided by Repo%s!",
				repo.getId()
			), versions,
			repo.getAvailableManifestSpecMajorVersions().stream().map(Object::toString).collect(Collectors.toList()));
		}


		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // Mapper default config
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        // Deserialize interfaces with correct implementations
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(ModPackage.class, BasicModPackage.class);
        mapper.registerModule(simpleModule);
        // Inject correct parent objects
		InjectableValues.Std injectableValues = new InjectableValues.Std();
		LookupTable lookupTable = new BasicLookupTable(repo);
        injectableValues.addValue(BasicLookupTable.class, lookupTable);
        mapper.setInjectableValues(injectableValues);

		try {
			List<LookupTableEntry> entries = Arrays.asList(mapper.readValue(new URL(String.format("%s/v%s/lookup-table.yaml", repo.getUri(), MAX_SHARED_VERSION)), BasicLookupTableEntry[].class));

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
