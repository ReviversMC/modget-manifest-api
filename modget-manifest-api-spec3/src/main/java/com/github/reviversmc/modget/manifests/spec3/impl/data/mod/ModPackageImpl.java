package com.github.reviversmc.modget.manifests.spec3.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.util.ModManifestDownloader;

public class ModPackageImpl implements ModPackage {
	private String packageId;
	private String publisher;
	private String modId;
	private String version;
	private List<ModManifest> manifests;


	@JsonCreator
	public ModPackageImpl(@JsonProperty("packageId") String packageId) {
		setPackageId(packageId);

		manifests = new ArrayList<>(1);
	}

	public ModPackageImpl(String publisher, String modId) {
		this.publisher = publisher;
		setModId(modId);

		manifests = new ArrayList<>(1);
	}


	@Override
	public String getPackageId() {
		return packageId;
	}

	@Override
	public void setPackageId(String packageId) {
		this.packageId = packageId;
        if (packageId != null) {
            String[] packageIdParts = packageId.split("\\.");
            publisher = packageIdParts[0];
            modId = packageIdParts[1];
        }
	}


	@Override
	public String getPublisher() {
		return publisher;
	}

	@Override
	public void setPublisher(String publisher) {
		this.publisher = publisher;
        if (publisher != null && modId != null) {
		    packageId = String.format("%s.%s", publisher, modId);
        }
	}


	@Override
	public String getModId() {
		return modId;
	}

	@Override
	public void setModId(String modId) {
		this.modId = modId;
        if (publisher != null && modId != null) {
		    packageId = String.format("%s.%s", publisher, modId);
        }
	}


	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public List<ModManifest> getManifests() {
		return manifests;
	}

	@Override
	public List<ModManifest> getOrDownloadManifests(List<ManifestRepository> repos) throws Exception {
        if (manifests.isEmpty()) {
            for (ManifestRepository repo : repos) {
                if (repo.getOrDownloadLookupTable() == null) continue;

                for (LookupTableEntry entry : repo.getLookupTable().getOrDownloadEntries()) {
                    if (entry.getId().equals(this.modId)) {
                        for (ModPackage modPackage : entry.getOrDownloadPackages()) {
                            this.addManifest(ModManifestDownloader.create().downloadModManifest(entry, this));
                        }
                        break;
                    }
                }
            }
        }
		return manifests;
	}

	@Override
	public void addManifest(ModManifest manifest) {
		manifests.add(manifest);
	}

	@Override
	public void setManifests(List<ModManifest> manifests) {
        if (manifests == null) {
            this.manifests.clear();
            return;
        }
		this.manifests = manifests;
	}

}
