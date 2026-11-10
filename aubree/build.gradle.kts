plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.trebronwayne.aubree"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
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
    implementation("androidx.core:core-ktx:1.13.1")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = project.group.toString()
                artifactId = "aubree"
                version = project.version.toString()
                pom {
                    name.set("Aubree")
                    description.set("Aubree the puppy: twelve animated states for Android apps.")
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
            // Only used by the release workflow, which provides these variables.
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
