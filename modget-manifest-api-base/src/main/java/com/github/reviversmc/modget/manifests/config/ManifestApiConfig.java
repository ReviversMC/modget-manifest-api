package com.github.reviversmc.modget.manifests.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManifestApiConfig {
    public static final String NAMESPACE = "modget-manifest-api";
    public static final String LOGGER_NAME = "Modget Manifest API";
    public static final List<Integer> KNOWN_MANIFEST_SPECS = new ArrayList<>(
        Arrays.asList(
            3, 4
        )
    );
}
