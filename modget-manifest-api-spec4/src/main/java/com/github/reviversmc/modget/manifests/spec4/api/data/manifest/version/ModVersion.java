package com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModVersionImpl;

@JsonDeserialize(as = ModVersionImpl.class)
public interface ModVersion {

	public ModManifest getParentManifest();
	public void setParentManifest(ModManifest parentManifest);

	public String getVersion();
	public void setVersion(String version);

	public List<ModVersionVariant> getVariants();
	public void setVariants(List<ModVersionVariant> variants);

}
