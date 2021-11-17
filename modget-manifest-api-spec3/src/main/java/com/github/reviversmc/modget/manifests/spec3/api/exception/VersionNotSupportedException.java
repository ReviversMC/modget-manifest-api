package com.github.reviversmc.modget.manifests.spec3.api.exception;

import java.util.List;

public class VersionNotSupportedException extends Exception {
	private final String message;
	private final List<String> supportedVersions;
	private final List<String> providedVersions;


	public VersionNotSupportedException(String message, List<String> supportedVersions, List<String> providedVersions) {
		super(message);
		this.message = message;

		this.supportedVersions = supportedVersions;
		this.providedVersions = providedVersions;
	}


	public String getMessage() {
		return message;
	}

	public List<String> getSupportedVersions() {
		return supportedVersions;
	}

	public List<String> getProvidedVersions() {
		return providedVersions;
	}

}
