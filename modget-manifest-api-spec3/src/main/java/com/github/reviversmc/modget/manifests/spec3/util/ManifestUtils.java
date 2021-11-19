package com.github.reviversmc.modget.manifests.spec3.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.reviversmc.modget.manifests.ManifestApiLogger;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec3.api.util.RepoHandlingUtilsBase;
import com.github.reviversmc.modget.manifests.spec3.config.ManifestApiSpec3Config;

public class ManifestUtils extends RepoHandlingUtilsBase {

	public static ManifestUtils create() {
		return new ManifestUtils();
	}


	public String assembleManifestUri(String uri, int manifestSpecMajorVersion, ModPackage modPackage) {
		return new String(String.format(
			"%s/v%s/manifests/%s/%s/%s/main.yaml",
			uri,
			manifestSpecMajorVersion,
			String.valueOf(modPackage.getPublisher().charAt(0)).toUpperCase(),
			modPackage.getPublisher(),
			modPackage.getModId())
		);
	}

	public ModManifest downloadManifest(LookupTableEntry entry, ModPackage modPackage) throws Exception {
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


		final String packageId = String.format("Repo%s.%s", repo.getId(), modPackage.getPackageId());
		final String uri = assembleManifestUri(
			repo.getUri(),
			MAX_SHARED_VERSION,
			modPackage
		);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ModPackage.class, modPackage);
        injectableValues.addValue(LookupTableEntry.class, entry);
        // injectableValues.addValue(ModManifest.class, null);
        // injectableValues.addValue(ModVersion.class, null);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		ModManifest modManifest;

		try {
			modManifest = mapper.readValue(new URL(uri), ModManifest.class);
		} catch (Exception e) {
			if (e instanceof IOException) {
				ManifestApiLogger.logWarn(String.format("An error occurred while fetching the %s manifest. Please check your Internet connection!", packageId), e.getStackTrace().toString());
			} else {
				ManifestApiLogger.logWarn(String.format("An error occurred while parsing the %s manifest", packageId), e.getStackTrace().toString());
			}
			throw e;
		}

		modManifest = setMissingReferences(modManifest);

		ManifestApiLogger.logInfo(String.format("Fetched Manifest: %s", packageId));
		return modManifest;
	}


	private ModManifest setMissingReferences(ModManifest modManifest) {
		// ThirdPartyIds
		ModThirdPartyIds thirdPartyIds = modManifest.getThirdPartyIds();
		thirdPartyIds.setParentManifest(modManifest);
		modManifest.setThirdPartyIds(thirdPartyIds);

		// Versions
		List<ModVersion> versions = new ArrayList<>();
		for (ModVersion version : modManifest.getDownloads()) {
			version.setParentManifest(modManifest);

			// Depends
			List<ModDownload> downloadPageUrls = new ArrayList<>();
			for (ModDownload downloadPageUrl : version.getDownloadPageUrls()) {
				downloadPageUrl.setParentModVersion(version);
				downloadPageUrls.add(downloadPageUrl);
			}
			version.setDownloadPageUrls(downloadPageUrls);

			// Depends
			List<ModDownload> fileUrls = new ArrayList<>();
			for (ModDownload fileUrl : version.getFileUrls()) {
				fileUrl.setParentModVersion(version);
				fileUrls.add(fileUrl);
			}
			version.setDownloadPageUrls(fileUrls);


			versions.add(version);
		}
		modManifest.setDownloads(versions);


		return modManifest;
	}

}
