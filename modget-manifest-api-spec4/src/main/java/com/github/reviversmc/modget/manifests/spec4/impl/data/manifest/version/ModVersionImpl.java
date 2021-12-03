package com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersionVariant;

public class ModVersionImpl implements ModVersion {
	private ModManifest parentManifest;
	private List<ModVersionVariant> variants;
	private String version;


	public ModVersionImpl(@JacksonInject ModManifest parentManifest) {
		this.parentManifest = parentManifest;

		this.variants = new ArrayList<>(2);
	}


	@Override
	public ModManifest getParentManifest() {
		return parentManifest;
	}

	@Override
	public void setParentManifest(ModManifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	@Override
	public List<ModVersionVariant> getVariants() {
		return variants;
	}

	@Override
	public void setVariants(List<ModVersionVariant> variants) {
        if (variants == null) {
            this.variants.clear();
            return;
        }
		this.variants = variants;
	}


	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

}
