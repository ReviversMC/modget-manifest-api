package com.github.nebelnidas.modget.manifest_api.spec3.impl.data.manifest.common;

import com.github.nebelnidas.modget.manifest_api.spec3.api.data.manifest.common.PackageIdVersionPair;

public class PackageIdVersionPairImpl implements PackageIdVersionPair {
	String packageId;
	String version;

	
	@Override
	public String getPackage() {
		return packageId;
	}

	@Override
	public void setPackage(String packageId) {
		this.packageId = packageId;
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
