package com.github.nebelnidas.modget.manifest_api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManifestApiConfig {
    public static final String NAMESPACE = "modget-manifest-api";
    public static final String LOGGER_NAME = "Modget Manifest API";

    public final static List<Integer> SUPPORTED_MANIFEST_SPECS = new ArrayList<>(
        Arrays.asList(
            3
        )
    );

}
