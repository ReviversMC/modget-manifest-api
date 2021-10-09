package com.github.nebelnidas.modget.manifest_api.api.v0.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.ManifestUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.RecognizedMod;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.PackageImpl;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.manifest.ManifestImpl;

public class ManifestUtilsImpl implements ManifestUtils {

	@Override
	public String assembleManifestUri(Repository repo, String publisher, String modId) {
		try {
			String uri = new String(String.format("%s/manifests/%s/%s/%s/%s.%s.yaml", repo.getUriWithSpec(), (""+publisher.charAt(0)).toUpperCase(), publisher, modId, publisher, modId));
			return uri;
		} catch (Exception e) {
			ManifestApi.logWarn(String.format("An error occurred while assembling the Repo%s.%s.%s manifest uri", repo.getId(), publisher, modId), e.getMessage());
			return null;
		}
	}

	@Override
	public Manifest downloadManifest(Repository repo, String publisher, String modId) {
		String packageId = String.format("Repo%s.%s.%s", repo.getId(), publisher, modId);
		String uri = assembleManifestUri(repo, publisher, modId);
		Manifest manifest;

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		try {
			manifest = mapper.readValue(new URL(uri), ManifestImpl.class);
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

	@Override
	public List<Package> getAvailablePackages(RecognizedMod mod) {
		List<Package> packages = new ArrayList<Package>();

		// For each repository
		for (LookupTableEntry entry : mod.getLookupTableEntries()) {
			Repository repo = entry.getParentLookupTable().getParentRepository();

			// For each package defined in the lookup table
			for (int j = 0; j < entry.getPackages().size(); j++) {
				String[] packageIdParts = entry.getPackages().get(j).toString().split("\\.");

				// Try to set the package metadata
				try {
					Manifest manifest = downloadManifest(repo, packageIdParts[0], packageIdParts[1]);
					if (manifest == null) {continue;}

					PackageImpl p = new PackageImpl(entry);
						p.setPublisher(manifest.getPublisher());
						p.setName(manifest.getName());
						p.setLicense(manifest.getLicense());
						p.setDescription(manifest.getDescription());
						p.setHome(manifest.getHome());
						p.setSource(manifest.getSource());
						p.setIssues(manifest.getIssues());
						p.setSupport(manifest.getSupport());
						p.setModType(manifest.getModType());
						p.setSide(manifest.getSide());
						p.setModVersions(manifest.getDownloads());
					packages.add(p);

				} catch (Exception e) {
					ManifestApi.logWarn(String.format("An error occurred while parsing the Repo%s.%s.%s manifest", repo.getId(), packageIdParts[0], packageIdParts[1]), e.getMessage());
				}
			}
		}
		return packages;
	}

}
