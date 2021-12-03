package com.github.reviversmc.modget.manifests.spec3.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.InstalledMod;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;

public class InstalledModImpl implements InstalledMod {
	private String id;
	private String installedVersion;
	private List<ModPackage> availablePackages;


	public InstalledModImpl(String id) {
		this.id = id;
		this.availablePackages = new ArrayList<>(2);
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String getInstalledVersion() {
		return installedVersion;
	}

	@Override
	public void setInstalledVersion(String installedVersion) {
		this.installedVersion = installedVersion;
	}


	@Override
	public List<ModPackage> getAvailablePackages() {
		return availablePackages;
	}

	@Override
	public List<ModPackage> getOrDownloadAvailablePackages(List<ManifestRepository> repos) throws Exception {
        if (availablePackages.isEmpty()) {

            for (ManifestRepository repo : repos) {
                if (repo.getOrDownloadLookupTable() == null) continue;

                for (LookupTableEntry entry : repo.getLookupTable().getOrDownloadEntries()) {
                    if (entry.getId().equals(this.id)) {
                        for (ModPackage modPackage : entry.getOrDownloadPackages()) {
                            this.addAvailablePackage(modPackage);
                        }
                        break;
                    }
                }
            }
        }
		return availablePackages;
	}

	@Override
	public void addAvailablePackage(ModPackage availablePackage) {
		availablePackages.add(availablePackage);
	}

	@Override
	public void setAvailablePackages(List<ModPackage> availablePackages) {
        if (availablePackages == null) {
            this.availablePackages.clear();
            return;
        }
		this.availablePackages = availablePackages;
	}

}
