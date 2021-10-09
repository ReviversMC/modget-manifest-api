package com.github.nebelnidas.modget.manifest_api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManifestApi {
    public static final String NAMESPACE = "modget-manifest-api";
    public static final String LOGGER_NAME = "Modget Manifest API";

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
