plugins {
    id("com.android.application") version "8.5.2" apply false
    id("com.android.library") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}

// Coordinates. JitPack and the release workflow pass -Pversion=<tag>, so the tag wins.
allprojects {
    group = "com.github.trebronwayne"
    if (version.toString() == "unspecified") {
        version = providers.gradleProperty("VERSION_NAME").get()
    }
}
