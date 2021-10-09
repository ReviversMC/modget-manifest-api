# Modget Library

This library contains common functions used by different modget tools.

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
implementation "com.github.ReviversMC:modget-manifest-api:${modget_lib_version}"
```
