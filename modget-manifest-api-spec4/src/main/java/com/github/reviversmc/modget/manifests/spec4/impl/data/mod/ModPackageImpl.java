package com.github.reviversmc.modget.manifests.spec4.impl.data.mod;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage;

public class ModPackageImpl implements ModPackage {
	private String packageId;
	private String publisher;
	private String modId;
	private List<String> loaders;
	private String version;
	private List<ModManifest> manifests;


    @JsonCreator
	public ModPackageImpl(@JsonProperty("packageId") String packageId) {
		setPackageId(packageId);

		loaders = new ArrayList<>(1);
		manifests = new ArrayList<>(1);
	}

    @JsonIgnore
	public ModPackageImpl(String publisher, String modId) {
		this.publisher = publisher;
		setModId(modId);

		loaders = new ArrayList<>(1);
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
	public List<String> getLoaders() {
		return loaders;
	}

	@Override
	public void setLoaders(List<String> loaders) {
		this.loaders = loaders;
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
	public void addManifest(ModManifest manifest) {
		manifests.add(manifest);
	}

	@Override
	public void setManifests(List<ModManifest> manifests) {
		this.manifests = manifests;
	}

}
