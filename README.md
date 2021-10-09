# Modget Manifest API

This API contains common functions for accessing Modget manifests and manifest repositories.

To use it, add jitpack to the end of your `build.gradle` repositories:
```gradle
repositories {
    ...
    maven {
        url = "https://jitpack.io"
    }
}
```

And then add modget-manifest-api to your dependencies:
```gradle
implementation "com.github.ReviversMC:modget-manifest-api:${modget_manifest_api_version}"
```
