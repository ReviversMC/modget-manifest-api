package com.github.reviversmc.modget.manifests.spec4.impl.downloaders;

import java.io.IOException;
import java.net.URL;
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
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModDownloads;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModDownloads;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModVersion;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.BasicModVersionVariant;
import com.github.reviversmc.modget.manifests.spec4.impl.data.mod.BasicModPackage;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class BasicModVersionDownloader extends RepoHandlingUtilsBase {

	public static BasicModVersionDownloader create() {
		return new BasicModVersionDownloader();
	}


	public String assembleUri(String uri, int manifestSpecMajorVersion, ModPackage modPackage, String version) {
		String[] versionParts = version.split("\\.");
		switch (versionParts.length) {
			case 0:
				return "";
			case 1:
				versionParts[1] = "0";
		}

		return new String(String.format(
			"%s/v%s/manifests/%s/%s/%s/%s/%s/%s.yaml",
			uri,
			manifestSpecMajorVersion,
			String.valueOf(modPackage.getPublisher().charAt(0)).toUpperCase(),
			modPackage.getPublisher(),
			modPackage.getModId(),
			String.format("%s.%s", versionParts[0], "x"),
			String.format("%s.%s.%s", versionParts[0], versionParts[1], "x"),
			version
		));
	}


	public ModVersion downloadModVersion(ModManifest modManifest, String version) throws Exception {
		ModPackage modPackage = modManifest.getParentPackage();
		ManifestRepository repo = modManifest.getParentLookupTableEntry().getParentLookupTable().getParentRepository();

		final int MAX_AVAILABLE_VERSION = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiConfig.KNOWN_MANIFEST_SPECS,
			repo.getAvailableManifestSpecMajorVersions()
		);

		String errorMessage = null;
		if (MAX_SHARED_VERSION == -1) {
			errorMessage = "This version of the Manifest API doesn't support any of the manifest specifications provided by Repo%s!";
		} else if (MAX_AVAILABLE_VERSION < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
			errorMessage = "ModVersions can only be downloaded for manifest spec v4 manifests or higher! For older ones, simply use ModManifest.getDownloads() with the back-compat module.";
		}
		if (errorMessage != null) {
			List<String> versions = Arrays.asList(Integer.toString(ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC));
			throw new VersionNotSupportedException(String.format(errorMessage, repo.getId()), versions,
					repo.getAvailableManifestSpecMajorVersions().stream().map(Object::toString).collect(Collectors.toList()));
		}


		final String packageIdWithRepo = String.format("Repo%s.%s", repo.getId(), modPackage.getPackageId());
		final String uri = assembleUri(
			repo.getUri(),
			MAX_SHARED_VERSION,
			modPackage,
			version
		);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // Mapper default config
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        // Deserialize interfaces with correct implementations
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(ModEnvironment.class, BasicModEnvironment.class);
        simpleModule.addAbstractTypeMapping(ModPackage.class, BasicModPackage.class);
        simpleModule.addAbstractTypeMapping(ModThirdPartyIds.class, BasicModThirdPartyIds.class);
        simpleModule.addAbstractTypeMapping(ModDownloads.class, BasicModDownloads.class);
        mapper.registerModule(simpleModule);
        // Inject correct parent objects
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
		final ModVersion modVersion = new BasicModVersion(modManifest);
        injectableValues.addValue(BasicModVersion.class, modVersion);
        injectableValues.addValue(BasicModVersionVariant.class, null);
        injectableValues.addValue(String.class, null);
        mapper.setInjectableValues(injectableValues);

		modVersion.setVersion(version);

		try {
			List<ModVersionVariant> modVersionVariants = Arrays.asList(mapper.readValue(new URL(uri), BasicModVersionVariant[].class));

			modVersion.setVariants(modVersionVariants);

            setMissingReferences(modVersion);

            ManifestApiLogger.logInfo(String.format("Fetched version manifest: %s %s", packageIdWithRepo, version));
            return modVersion;

		} catch (Exception e) {
			if (e instanceof IOException) {
				ManifestApiLogger.logWarn(String.format("An error occurred while fetching the %s %s version manifest. Please check your Internet connection!", packageIdWithRepo, version), ExceptionUtils.getStackTrace(e));
			} else {
				ManifestApiLogger.logWarn(String.format("An error occurred while parsing the %s %s version manifest", packageIdWithRepo, version), ExceptionUtils.getStackTrace(e));
			}
			throw e;
		}
	}


	private void setMissingReferences(ModVersion modVersion) {
		ModManifest modManifest = modVersion.getParentManifest();

		for (ModVersionVariant variant : modVersion.getVariants()) {
			variant.setParentVersion(modVersion);

			// ThirdPartyIds
			variant.getThirdPartyIds().setParentModVersionVariant(variant);

			// Environment
			variant.getEnvironment().setParentModVersionVariant(variant);

			// Depends
			for (ModPackage modPackage : variant.getDepends()) {
				modPackage.addManifest(modManifest);
			}

			// Bundles
			for (ModPackage modPackage : variant.getBundles()) {
				modPackage.addManifest(modManifest);
			}

			// Breaks
			for (ModPackage modPackage : variant.getBreaks()) {
				modPackage.addManifest(modManifest);
			}

			// Conflicts
			for (ModPackage modPackage : variant.getConflicts()) {
				modPackage.addManifest(modManifest);
			}

			// Recommends
			for (ModPackage modPackage : variant.getRecommends()) {
				modPackage.addManifest(modManifest);
			}

			// DownloadPageUrls
			variant.getDownloadPageUrls().setParentModVersionVariant(variant);

			// FileUrls
			variant.getFileUrls().setParentModVersionVariant(variant);
		}
	}

}
