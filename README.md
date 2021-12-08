# Modget Manifest API

This API provides basic functionality for accessing Modget manifests and manifest repositories.

To use it, add jitpack to the end of your `build.gradle` repositories:
```gradle
repositories {
    ...
    maven {
        url = "https://jitpack.io"
    }
}
```

From here on, it depends on what you're trying to achieve. If you already know you're going to work with a repository using a specific manifest spec, let's say v4, you can simply add the `modget-manifest-api-spec4` package to your dependencies:

```gradle
api "com.github.ReviversMC.modget-manifest-api:spec4:${modget_manifest_api_version}"
```

But what if you need to support multiple generations of manifest specifications? [Modget-Minecraft](https://github.com/ReviversMC/modget-minecraft) for example allows users to add custom repositories, though it can't expect that these will always be up-to-date with the latest spec. Hence, we created a compatibility module which you can add this way:
```gradle
implementation "com.github.ReviversMC.modget-manifest-api:modget-manifest-api-compat:${modget_manifest_api_version}"
```

Additionally, you still need to implement a specific manfifest specification's API like above. This method allows you to work with features from the later manifest specs, while still retaining backwards compatibility with the older ones. Be aware though that trying to read certain values introduced by newer specs from a manifest with an old spec will return in a `null` value, so you should handle these cases well.
