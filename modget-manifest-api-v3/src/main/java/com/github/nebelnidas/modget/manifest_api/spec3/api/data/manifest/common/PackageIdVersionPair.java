package com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.common.PackageIdVersionPairImpl;

@JsonDeserialize(as = PackageIdVersionPairImpl.class)
public interface PackageIdVersionPair {

	public String getPackage();
	public void setPackage(String packageId);

	public String getVersion();
	public void setVersion(String version);

}
