package com.github.reviversmc.modget.manifests.spec3.impl.data.manifest.common;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.common.PackageIdVersionPair;

public class PackageIdVersionPairImpl implements PackageIdVersionPair {
	String packageId;
	String version;


	public PackageIdVersionPairImpl(@JacksonInject String packageId, @JacksonInject String version) {
		this.packageId = packageId;
		this.version = version;
	}


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
