package com.github.nebelnidas.modget.manifest_api.spec4.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManifestApiV4Config {
    public final static List<Integer> SUPPORTED_MANIFEST_SPECS = new ArrayList<>(
        Arrays.asList(
            3, 4
        )
    );
    public final static int MIN_SUPPORTED_VERSION = SUPPORTED_MANIFEST_SPECS.get(0);
    public final static int MAX_SUPPORTED_VERSION = SUPPORTED_MANIFEST_SPECS.get(SUPPORTED_MANIFEST_SPECS.size() - 1);

}
