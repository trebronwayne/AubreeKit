# AubreeKit

Aubree the puppy, ready to drop into any Android app. Twelve animated states, one line of code.

| State | Use it for |
|---|---|
| `HAPPY` | success, done, saved |
| `SAD` | error, failed, declined |
| `ASK` | request, permission, confirm |
| `WAIT` | loading, processing |
| `CELEBRATE` | big win, level up, first purchase |
| `SLEEPY` | idle, night mode, nothing happening |
| `OFFLINE` | no connection, retry |
| `EMPTY` | empty list, no results, no items |
| `SEARCHING` | search in progress |
| `TYPING` | composing, saving text, chat input |
| `LOW_BATTERY` | low battery or power-saving warning |
| `WELCOME` | welcome back, onboarding, login success |

## Add it to your app

**1. Add JitPack** to `settings.gradle.kts` (or `settings.gradle`):

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

**2. Add the dependency** in your app module:

```kotlin
dependencies {
    // Classic Views (XML)
    implementation("com.github.trebronwayne.AubreeKit:aubree:1.0.0")

    // Jetpack Compose (includes the Views artifact)
    implementation("com.github.trebronwayne.AubreeKit:aubree-compose:1.0.0")
}
```

Groovy: `implementation 'com.github.trebronwayne.AubreeKit:aubree:1.0.0'`

## Use it

**Compose**

```kotlin
Aubree(state = AubreeState.WAIT, size = 160.dp)
```

**XML**

```xml
<com.trebronwayne.aubree.AubreeView
    android:layout_width="160dp"
    android:layout_height="160dp"
    app:aubreeState="wait" />
```

```kotlin
binding.aubree.state = AubreeState.HAPPY   // switch at any time
```

**A typical flow**

```kotlin
val aubree = when (uiState) {
    Loading -> AubreeState.WAIT
    Success -> AubreeState.HAPPY
    Error -> AubreeState.SAD
    Offline -> AubreeState.OFFLINE
    Empty -> AubreeState.EMPTY
}
Aubree(state = aubree)
```

## Good to know

- **Android 9 (API 28) and up:** the animation plays and loops.
- **Android 5 to 8 (API 21 to 27):** a still picture of the same state is shown.
- **Animations turned off** in system settings: the still picture is shown.
- Playback stops when the view leaves the screen, so it does not use battery in the background.
- Each state has a built-in screen-reader description. Set your own with `android:contentDescription`
  (XML) or the `contentDescription` parameter (Compose).
- The whole library adds about 3.5 MB to your APK before R8, in a single `drawable-nodpi` folder.
- Minimum SDK 21. Compose wrapper built with Compose BOM 2024.06.00.

## Try the sample

Open this folder in Android Studio and run the `sample` configuration. It shows all twelve states.

## Project layout

```
aubree/            core library: AubreeView, AubreeState, the animation files
aubree-compose/    Compose wrapper: Aubree(...)
sample/            demo app
.github/workflows/ ci.yml and release.yml
jitpack.yml        tells JitPack to build with JDK 17
```

## Publishing a new version (maintainer)

1. Push your changes to `main`. The **CI** workflow builds, lints and tests, and keeps the AAR and a
   sample APK as downloadable artifacts.
2. Tag a release:

   ```bash
   git tag v1.0.1
   git push origin v1.0.1
   ```

3. The **Release** workflow builds the tag, attaches the two AAR files to a GitHub Release,
   and publishes to GitHub Packages.
4. [JitPack](https://jitpack.io/#trebronwayne/AubreeKit) builds the same tag the first time
   someone requests it. Open that page and press **Get it** next to the tag to build it right away.

## License

Code: MIT, see `LICENSE`. The Aubree artwork belongs to its creator; keep the credit when you
redistribute the images.
