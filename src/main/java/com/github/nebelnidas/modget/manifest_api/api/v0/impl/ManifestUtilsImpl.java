package com.github.nebelnidas.modget.manifest_api.api.v0.impl;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.ManifestUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;

public class ManifestUtilsImpl implements ManifestUtils {

	public static ManifestUtilsImpl create() {
		return new ManifestUtilsImpl();
	}


	@Override
	public String assembleManifestUri(Repository repo, String publisher, String modId) {
		try {
			final String uri = new String(String.format("%s/manifests/%s/%s/%s/%s.%s.yaml", repo.getUriWithSpec(), (""+publisher.charAt(0)).toUpperCase(), publisher, modId, publisher, modId));
			return uri;
		} catch (Exception e) {
			ManifestApi.logWarn(String.format("An error occurred while assembling the Repo%s.%s.%s manifest uri", repo.getId(), publisher, modId), e.getMessage());
			return null;
		}
	}

	@Override
	public Manifest downloadManifest(LookupTableEntry entry, Package pack) {
		final String packageId = String.format("Repo%s.%s.%s", entry.getParentLookupTable().getParentRepository().getId(), pack.getPublisher(), pack.getId());
		final String uri = assembleManifestUri(entry.getParentLookupTable().getParentRepository(), pack.getPublisher(), pack.getId());

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(Package.class, pack);
        injectableValues.addValue(LookupTableEntry.class, entry);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		Manifest manifest;

		try {
			manifest = mapper.readValue(new URL(uri), Manifest.class);
		} catch (Exception e) {
			if (e instanceof IOException) {
				ManifestApi.logWarn(String.format("An error occurred while fetching the %s manifest. Please check your Internet connection!", packageId), e.getMessage());
			} else {
				ManifestApi.logWarn(String.format("An error occurred while parsing the %s manifest", packageId), e.getMessage());
			}
			return null;
		}
		ManifestApi.logInfo(String.format("Fetched Manifest: %s", packageId));
		return manifest;
	}

}
