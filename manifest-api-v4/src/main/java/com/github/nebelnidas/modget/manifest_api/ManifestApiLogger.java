package com.github.nebelnidas.modget.manifest_api;

import com.github.nebelnidas.modget.manifest_api.config.ManifestApiConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManifestApiLogger {

    private static Logger getLogger() {
        return LogManager.getLogger(ManifestApiConfig.LOGGER_NAME);
    }

    public static void logWarn(String description, String error) {
        getLogger().warn(String.format("%s: %s", description, error));
    }

    public static void logInfo(String info) {
        getLogger().info(info);
    }
}
