package com.github.reviversmc.modget.manifests.spec3.util;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.reviversmc.modget.manifests.ManifestApiLogger;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec3.api.util.RepoHandlingUtilsBase;
import com.github.reviversmc.modget.manifests.spec3.config.ManifestApiSpec3Config;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ModManifestDownloader extends RepoHandlingUtilsBase {

	public static ModManifestDownloader create() {
		return new ModManifestDownloader();
	}


	public String assembleManifestUri(String uri, int manifestSpecMajorVersion, ModPackage modPackage) {
		return new String(String.format(
			"%s/v%s/manifests/%s/%s/%s/%s.yaml",
			uri,
			manifestSpecMajorVersion,
			String.valueOf(modPackage.getPublisher().charAt(0)).toUpperCase(),
			modPackage.getPublisher(),
			modPackage.getModId(),
			modPackage.getPackageId()
		));
	}

	public ModManifest downloadModManifest(LookupTableEntry entry, ModPackage modPackage) throws Exception {
		ManifestRepository repo = entry.getParentLookupTable().getParentRepository();

		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiSpec3Config.SUPPORTED_MANIFEST_SPEC,
			repo.getAvailableManifestSpecMajorVersions()
		);

		if (MAX_SHARED_VERSION == -1) {
			throw new VersionNotSupportedException(String.format(
				"This version of the Manifest API doesn't support any of the manifest specifications provided by Repo%s!",
				repo.getId()
			), new ArrayList<String>(
				Arrays.asList(
					Integer.toString(ManifestApiSpec3Config.SUPPORTED_MANIFEST_SPEC)
				)
			),
			repo.getAvailableManifestSpecMajorVersions().stream().map(Object::toString).collect(Collectors.toList()));
		}


		final String packageIdWithRepo = String.format("Repo%s.%s", repo.getId(), modPackage.getPackageId());
		final String uri = assembleManifestUri(
			repo.getUri(),
			MAX_SHARED_VERSION,
			modPackage
		);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ModPackage.class, modPackage);
        injectableValues.addValue(LookupTableEntry.class, entry);
        injectableValues.addValue(ModManifest.class, null);
        injectableValues.addValue(ModVersion.class, null);
        injectableValues.addValue(String.class, null);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

		ModManifest modManifest;

		try {
			modManifest = mapper.readValue(new URL(uri), ModManifest.class);
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
		// ThirdPartyIds
		modManifest.getThirdPartyIds().setParentManifest(modManifest);

		// Versions
		for (ModVersion version : modManifest.getDownloads()) {
			version.setParentManifest(modManifest);

			// DownloadPageUrls
			for (ModDownload downloadPageUrl : version.getDownloadPageUrls()) {
				downloadPageUrl.setParentModVersion(version);
			}

			// FileUrls
			for (ModDownload fileUrl : version.getFileUrls()) {
				fileUrl.setParentModVersion(version);
			}
		}
	}

}
