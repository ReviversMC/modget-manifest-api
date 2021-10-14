package com.github.nebelnidas.modget.manifest_api;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.ManifestUtils;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.ManifestUtilsImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManifestApi {
    public static final String NAMESPACE = "modget-manifest-api";
    public static final String LOGGER_NAME = "Modget Manifest API";

    public static final ManifestUtils MANIFEST_UTILS = new ManifestUtilsImpl();

    private static Logger getLogger() {
        return LogManager.getLogger(LOGGER_NAME);
    }

    public static void logWarn(String description, String error) {
        getLogger().warn(String.format("%s: %s", description, error));
    }

    public static void logInfo(String info) {
        getLogger().info(info);
    }
}
