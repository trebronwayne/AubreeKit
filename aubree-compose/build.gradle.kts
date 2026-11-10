plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.trebronwayne.aubree.compose"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        // Matches Kotlin 1.9.24.
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    lint {
        abortOnError = false
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api(project(":aubree"))

    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = project.group.toString()
                artifactId = "aubree-compose"
                version = project.version.toString()
                pom {
                    name.set("Aubree Compose")
                    description.set("Jetpack Compose wrapper for Aubree.")
                    url.set("https://github.com/trebronwayne/AubreeKit")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    scm {
                        url.set("https://github.com/trebronwayne/AubreeKit")
                        connection.set("scm:git:https://github.com/trebronwayne/AubreeKit.git")
                    }
                }
            }
        }
        repositories {
            val actor = System.getenv("GITHUB_ACTOR")
            val token = System.getenv("GITHUB_TOKEN")
            if (actor != null && token != null) {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/trebronwayne/AubreeKit")
                    credentials {
                        username = actor
                        password = token
                    }
                }
            }
        }
    }
}
