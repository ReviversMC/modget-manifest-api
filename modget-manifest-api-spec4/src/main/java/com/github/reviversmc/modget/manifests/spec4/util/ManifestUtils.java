package com.github.reviversmc.modget.manifests.spec4.util;

import java.io.IOException;
import java.lang.reflect.Method;
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
import com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModAuthor;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModChats;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModDownloads;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModEnvironment;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModThirdPartyIds;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec4.api.exception.VersionNotSupportedException;
import com.github.reviversmc.modget.manifests.spec4.api.util.RepoHandlingUtilsBase;
import com.github.reviversmc.modget.manifests.spec4.config.ManifestApiSpec4Config;

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

		final int MAX_AVAILABLE_VERSION = repo.getAvailableManifestSpecMajorVersions().get(repo.getAvailableManifestSpecMajorVersions().size() - 1);
		final int MAX_SHARED_VERSION = findMaxSharedInt(
			ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC,
			repo.getAvailableManifestSpecMajorVersions()
		);


		boolean notSupported = false;
		if (MAX_SHARED_VERSION == -1) {
			notSupported = true;
		} else if (MAX_AVAILABLE_VERSION < ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC) {
			ManifestApiLogger.logInfo("Utilizing back-compat module...");

			String packageName = "com.github.reviversmc.modget.manifests.compat.spec3";
			String className = "Spec3ToSpec4ManifestCompat";
			String convertMethodName = "downloadAndConvertManifest";
			Class<?>[] formalParameters = { LookupTableEntry.class, ModPackage.class };
			Object[] effectiveParameters = new Object[] { entry, modPackage };

			try {
				Class<?> Spec3ToSpec4ManifestCompat = Class.forName(packageName + "." + className);

				Method method = Spec3ToSpec4ManifestCompat.getMethod(convertMethodName, formalParameters);
				Object newInstance = Spec3ToSpec4ManifestCompat.newInstance();
				Object value = method.invoke(newInstance, effectiveParameters);

				return (ModManifest)value;

			} catch (Exception e) {
				ManifestApiLogger.logInfo("Back-compat module has failed! " + e.getStackTrace());
			}
			notSupported = true;
		}

		if (notSupported) {
			throw new VersionNotSupportedException(String.format(
				"This version of the Manifest API doesn't support any of the manifest specifications provided by Repo%s!",
				repo.getId()
			), new ArrayList<String>(
				Arrays.asList(
					Integer.toString(ManifestApiSpec4Config.SUPPORTED_MANIFEST_SPEC)
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
		// Authors
		List<ModAuthor> authors = new ArrayList<>();
		for (ModAuthor author : modManifest.getAuthors()) {
			author.setParentManifest(modManifest);
			authors.add(author);
		}
		modManifest.setAuthors(authors);


		// Chats
		ModChats chats = modManifest.getChats();
		chats.setParentManifest(modManifest);
		modManifest.setChats(chats);


		// Versions
		List<ModVersion> versions = new ArrayList<>();
		for (ModVersion version : modManifest.getVersions()) {
			version.setParentManifest(modManifest);

			// Environment
			ModEnvironment environment = version.getEnvironment();
			environment.setParentModVersion(version);
			version.setEnvironment(environment);

			// Depends
			List<ModPackage> depends = new ArrayList<>();
			for (ModPackage modPackage : version.getDepends()) {
				modPackage.addManifest(modManifest);
				depends.add(modPackage);
			}
			version.setDepends(depends);

			// Bundles
			List<ModPackage> bundles = new ArrayList<>();
			for (ModPackage modPackage : version.getBundles()) {
				modPackage.addManifest(modManifest);
				bundles.add(modPackage);
			}
			version.setBundles(bundles);

			// Breaks
			List<ModPackage> breaks = new ArrayList<>();
			for (ModPackage modPackage : version.getBreaks()) {
				modPackage.addManifest(modManifest);
				breaks.add(modPackage);
			}
			version.setBreaks(breaks);

			// Conflicts
			List<ModPackage> conflicts = new ArrayList<>();
			for (ModPackage modPackage : version.getConflicts()) {
				modPackage.addManifest(modManifest);
				conflicts.add(modPackage);
			}
			version.setConflicts(conflicts);

			// Recommends
			List<ModPackage> recommends = new ArrayList<>();
			for (ModPackage modPackage : version.getRecommends()) {
				modPackage.addManifest(modManifest);
				recommends.add(modPackage);
			}
			version.setRecommends(recommends);

			// ThirdPartyIds
			ModThirdPartyIds thirdPartyIds = version.getThirdPartyIds();
			thirdPartyIds.setParentModVersion(version);
			version.setThirdPartyIds(thirdPartyIds);

			// DownloadPageUrls
			ModDownloads downloadPageUrls = version.getDownloadPageUrls();
			downloadPageUrls.setParentModVersion(version);
			version.setDownloadPageUrls(downloadPageUrls);

			// FileUrls
			ModDownloads fileUrls = version.getFileUrls();
			fileUrls.setParentModVersion(version);
			version.setFileUrls(fileUrls);


			versions.add(version);
		}
		modManifest.setVersions(versions);


		return modManifest;
	}

}
