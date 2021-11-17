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

From here on, it depends on what you're trying to do. If you already know you're going to work with a repository using a specific manifest spec, let's say v4, you can simply add the `modget-manifest-api-spec4` package to your dependencies:

```gradle
implementation "com.github.ReviversMC:modget-manifest-api-spec4:${modget_manifest_api_version}"
```

If you want to stay on the bleeding edge and always use the latest available specification, simply depend on the `modget-manifest-api` package without any specific spec versions attached.

But what if you need to support multiple generations of manifest specifications? [Modget-Minecraft]() for example allows users to add custom repositories, though it can't expect that these will always be up-to-date with the latest spec. Hence, we created a compatibility module which you can additionally add this way:
```gradle
implementation "com.github.ReviversMC:modget-manifest-api-compat:${modget_manifest_api_version}"
```

This method allows you to work with the latest and greatest features of the latest manifest spec, while still retaining backwards compatibility. Be aware though that trying to read certain values introduced by newer specs from a manifest with an old spec will return in a `null` value, so you should handle these cases well.
