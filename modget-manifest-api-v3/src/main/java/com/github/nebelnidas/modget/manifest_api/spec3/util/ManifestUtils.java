package com.github.nebelnidas.modget.manifest_api.spec3.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApiLogger;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.ManifestRepository;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.main.ModManifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.version.ModVersion;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.mod.ModPackage;

public class ManifestUtils {

	public static ManifestUtils create() {
		return new ManifestUtils();
	}


	public String assembleManifestUri(ManifestRepository repo, String publisher, String modId) {
		// try {
			final String uri = new String(String.format("%s/v%s/manifests/%s/%s/%s/%s.%s.yaml", repo.getUri(), repo.getMaxSpecVersion(), (""+publisher.charAt(0)).toUpperCase(), publisher, modId, publisher, modId));
			return uri;
		// } catch (Exception e) {
		// 	ManifestApiLogger.logWarn(String.format("An error occurred while assembling the Repo%s.%s.%s manifest uri", repo.getId(), publisher, modId), e.getMessage());
		// 	throw e;
		// }
	}

	public ModManifest downloadManifest(LookupTableEntry entry, ModPackage pack) throws Exception {
		final String packageId = String.format("Repo%s.%s.%s", entry.getParentLookupTable().getParentRepository().getId(), pack.getPublisher(), pack.getId());
		final String uri = assembleManifestUri(entry.getParentLookupTable().getParentRepository(), pack.getPublisher(), pack.getId());

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ModPackage.class, pack);
        injectableValues.addValue(LookupTableEntry.class, entry);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		ModManifest manifest;

		try {
			manifest = mapper.readValue(new URL(uri), ModManifest.class);

			// TODO: Figure out how to set parentManifest with Jackson
			List<ModVersion> versions = new ArrayList<>();
			for (int i = 0; i < manifest.getDownloads().size(); i++) {
				ModVersion version = manifest.getDownloads().get(i);
				version.setParentManifest(manifest);
				versions.add(version);
			}
			manifest.setDownloads(versions);

		} catch (Exception e) {
			if (e instanceof IOException) {
				ManifestApiLogger.logWarn(String.format("An error occurred while fetching the %s manifest. Please check your Internet connection!", packageId), e.getMessage());
			} else {
				ManifestApiLogger.logWarn(String.format("An error occurred while parsing the %s manifest", packageId), e.getMessage());
			}
			throw e;
		}
		ManifestApiLogger.logInfo(String.format("Fetched Manifest: %s", packageId));
		return manifest;
	}

}
