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
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModAuthor;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModChats;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;
import com.github.reviversmc.modget.manifests.spec4.impl.data.lookuptable.BasicLookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.BasicModAuthor;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.BasicModChats;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.BasicModManifest;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModVersion;
import com.github.reviversmc.modget.manifests.spec4.impl.data.mod.BasicModPackage;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class BasicModManifestDownloader extends RepoHandlingUtilsBase {

	public static BasicModManifestDownloader create() {
		return new BasicModManifestDownloader();
	}


	public String assembleUri(String uri, int manifestSpecMajorVersion, ModPackage modPackage) {
		return new String(String.format(
			"%s/v%s/manifests/%s/%s/%s/main.yaml",
			uri,
			manifestSpecMajorVersion,
			String.valueOf(modPackage.getPublisher().charAt(0)).toUpperCase(),
			modPackage.getPublisher(),
			modPackage.getModId()
		));
	}


	public ModManifest downloadModManifest(LookupTableEntry entry, ModPackage modPackage) throws Exception {
		ManifestRepository repo = entry.getParentLookupTable().getParentRepository();
		final int MAX_AVAILABLE_VERSION = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiConfig.KNOWN_MANIFEST_SPECS,
			repo.getAvailableManifestSpecMajorVersions()
		);

		final String backCompatClassPath = "com.github.reviversmc.modget.manifests.compat.spec3.Spec3ToSpec4ManifestCompat";
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

				String convertMethodName = "downloadAndConvertManifest";
				Class<?>[] formalParameters = { LookupTableEntry.class, ModPackage.class };
				Object[] effectiveParameters = new Object[] { entry, modPackage };

				try {
					Class<?> Spec3ToSpec4ManifestCompat = Class.forName(backCompatClassPath);
					Method method = Spec3ToSpec4ManifestCompat.getMethod(convertMethodName, formalParameters);
					Object newInstance = Spec3ToSpec4ManifestCompat.getDeclaredConstructor().newInstance();
					Object value;
					try {
						value = method.invoke(newInstance, effectiveParameters);
						return (ModManifest)value;
					} catch (Exception e) {
						ManifestApiLogger.logWarn("Error in back-compat module encountered", ExceptionUtils.getStackTrace(e));
					}
					return null;

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


		final String packageIdWithRepo = String.format("Repo%s.%s", repo.getId(), modPackage.getPackageId());
		final String uri = assembleUri(
			repo.getUri(),
			MAX_SHARED_VERSION,
			modPackage
		);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // Mapper default config
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        // Deserialize interfaces with correct implementations
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(ModPackage.class, BasicModPackage.class);
        simpleModule.addAbstractTypeMapping(ModAuthor.class, BasicModAuthor.class);
        simpleModule.addAbstractTypeMapping(ModChats.class, BasicModChats.class);
        simpleModule.addAbstractTypeMapping(ModVersion.class, BasicModVersion.class);
        mapper.registerModule(simpleModule);
        // Inject correct parent objects
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(BasicModPackage.class, modPackage);
        injectableValues.addValue(BasicLookupTableEntry.class, entry);
        injectableValues.addValue(BasicModManifest.class, null);
        injectableValues.addValue(BasicModVersion.class, null);
        injectableValues.addValue(String.class, null);
        mapper.setInjectableValues(injectableValues);

		ModManifest modManifest;

		try {
			modManifest = mapper.readValue(new URL(uri), BasicModManifest.class);
		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				ManifestApiLogger.logWarn(String.format("An error occurred while fetching the %s manifest. Please check your Internet connection!", packageIdWithRepo), ExceptionUtils.getStackTrace(e));
			} else {
				ManifestApiLogger.logWarn(String.format("An error occurred while parsing the %s manifest", packageIdWithRepo), ExceptionUtils.getStackTrace(e));
			}
			throw e;
		}

		setMissingReferences(modManifest);

		ManifestApiLogger.logInfo(String.format("Fetched Manifest: %s", packageIdWithRepo));
		return modManifest;
	}


	private void setMissingReferences(ModManifest modManifest) {
		// Authors
		for (ModAuthor author : modManifest.getAuthors()) {
			author.setParentManifest(modManifest);
		}

		// Chats
		modManifest.getChats().setParentManifest(modManifest);

		// Versions
		for (ModVersion version : modManifest.getVersions()) {
			version.setParentManifest(modManifest);
		}
	}

}
