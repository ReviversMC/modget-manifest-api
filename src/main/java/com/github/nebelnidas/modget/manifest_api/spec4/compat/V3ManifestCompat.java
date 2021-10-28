package com.github.nebelnidas.modget.manifest_api.spec4.compat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.Package;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.Manifest;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ModVersion;
import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.ModVersionDownload;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.PackageImpl;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.lookuptable.LookupTableEntryImpl;
import com.github.nebelnidas.modget.manifest_api.spec3.util.ManifestUtils;

public class V3ManifestCompat {

	public static V3ManifestCompat create() {
		return new V3ManifestCompat();
	}


	public com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.Manifest downloadAndConvertManifest(
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry v4Entry,
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.Package v4Package
	) throws IOException
	{
		Package v3Package = new PackageImpl(v4Package.getPublisher(), v4Package.getId());
		LookupTableEntry v3Entry = new LookupTableEntryImpl(null);

		Manifest v3Manifest = ManifestUtils.create().downloadManifest(v3Entry, v3Package);

		return convertManifest(v3Manifest, v4Package, v4Entry);
	}



	public com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.Manifest convertManifest(
		Manifest v3Manifest, com.github.nebelnidas.modget.manifest_api.spec4.api.data.Package v4Package,
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.lookuptable.LookupTableEntry v4Entry
	)
	{
		// Create new v4 manifest
		com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.Manifest v4Manifest
		= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ManifestImpl(
			v4Package, v4Entry
		);
		// Copy basic metadata
		v4Manifest.setDescription(v3Manifest.getDescription());
		v4Manifest.setHome(v3Manifest.getHome());
		v4Manifest.setId(v3Manifest.getId());
		v4Manifest.setIssues(v3Manifest.getIssues());
		v4Manifest.setLicense(v3Manifest.getLicense());
		v4Manifest.setManifestSpecVersion(v3Manifest.getManifestSpecVersion());
		v4Manifest.setModType(v3Manifest.getModType());
		v4Manifest.setName(v3Manifest.getName());
		v4Manifest.setPublisher(v3Manifest.getPublisher());
		v4Manifest.setSide(v3Manifest.getSide());
		v4Manifest.setSource(v3Manifest.getSource());
		v4Manifest.setSupport(v3Manifest.getSupport());

		// Copy thirdPartyIds
		v4Manifest.setThirdPartyIds(new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ThirdPartyIdsImpl() {{
			setCurseforge(v3Manifest.getThirdPartyIds().getCurseforge());
			setModrinth(v3Manifest.getThirdPartyIds().getModrinth());
		}});

		// Copy mod versions
		List<com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersion> v4ModVersions = new ArrayList<>();
		for (ModVersion v3Version : v3Manifest.getDownloads()) {
			com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersion v4Version
				= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ModVersionImpl(v4Manifest);

			// Copy version metadata
			v4Version.setMd5(v3Version.getMd5());
			v4Version.setMinecraftVersions(v3Version.getMinecraftVersions());
			v4Version.setVersion(v3Version.getVersion());

			// Copy version download page urls
			List<com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersionDownload> v4ModVersionDownloadPageUrls = new ArrayList<>();
			for (ModVersionDownload v3VersionDownload : v3Version.getDownloadPageUrls()) {
				com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersionDownload v4VersionDownloadPage
					= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ModVersionDownloadImpl();

				v4VersionDownloadPage.setName(v3VersionDownload.getName());
				v4VersionDownloadPage.setUrl(v3VersionDownload.getUrl());

				v4ModVersionDownloadPageUrls.add(v4VersionDownloadPage);
			}

			// Copy version file urls
			List<com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersionDownload> v4ModVersionFileUrls = new ArrayList<>();
			for (ModVersionDownload v3VersionDownload : v3Version.getFileUrls()) {
				com.github.nebelnidas.modget.manifest_api.spec4.api.data.manifest.ModVersionDownload v4VersionFileUrl
					= new com.github.nebelnidas.modget.manifest_api.spec4.impl.data.manifest.ModVersionDownloadImpl();

				v4VersionFileUrl.setName(v3VersionDownload.getName());
				v4VersionFileUrl.setUrl(v3VersionDownload.getUrl());

				v4ModVersionFileUrls.add(v4VersionFileUrl);
			}

			v4ModVersions.add(v4Version);
		}

		v4Manifest.setDownloads(v4ModVersions);

		return v4Manifest;
	}
}
