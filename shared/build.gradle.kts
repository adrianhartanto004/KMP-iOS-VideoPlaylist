import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.0"
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
//    id(Dependencies.Plugins.mokoResources)
    id(Dependencies.Plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.quipper.kmmplaylistexercise.shared"
    defaultConfigs {
    }
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = false
        }
    }

    cocoapods {
        summary = "Kmm Playlist Exercise shared module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.SqlDelight.runtime)
                implementation(Dependencies.Ktor.clientCore)
                implementation(Dependencies.Ktor.clientCio)
                implementation(Dependencies.Ktor.clientSerialization)
//                api(Dependencies.Moko.resources)
                implementation(Dependencies.Koin.core)
                implementation(Dependencies.Coroutines.core) {
                    isForce = true
                }
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.SqlDelight.androidDriver)
                implementation(Dependencies.Android.material)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Dependencies.SqlDelight.nativeDriver)
                implementation(Dependencies.Ktor.clientIos)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(Dependencies.Ktor.clientMock)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Dependencies.AndroidTest.junit)
                implementation(Dependencies.SqlDelight.driver)
                implementation(Dependencies.AndroidXTest.core)
                implementation(Dependencies.AndroidXTest.junit)
                implementation(Dependencies.Test.robolectric)
            }
        }

        val iosTest by getting
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.quipper.kmmplaylistexercise.shared.cache"
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

//multiplatformResources {
//    multiplatformResourcesPackage = "com.quipper.kmmplaylistexercise.shared" // required
//}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//    if (this !is KotlinNativeTarget) return@configureEach
//
//    val arch = when (this.konanTarget) {
//        org.jetbrains.kotlin.konan.target.KonanTarget.IOS_ARM64 -> "iosarm64"
//        org.jetbrains.kotlin.konan.target.KonanTarget.IOS_X64 -> "iosx64"
//        else -> throw IllegalArgumentException()
//    }
//
//    this.binaries.configureEach {
//        if (this is org.jetbrains.kotlin.gradle.plugin.mpp.Framework) {
//            this.export("dev.icerock.moko:resources-$arch:0.13.2")
//        }
//    }
//}
