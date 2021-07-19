sealed class Dependencies {
    object Plugins {
//        const val mokoResources = "dev.icerock.mobile.multiplatform-resources"
        const val buildKonfig = "com.codingfeline.buildkonfig"
    }
    object Versions {
        const val sqlDelight = "1.5.0"
        const val robolectric = "4.4"
        const val kotlin = "1.5.20"
        const val ktor = "1.6.0"
//        const val mokoResources = "0.15.1"
        const val koin = "3.0.1"
        const val coroutines = "1.5.0-native-mt"
        const val konfig = "0.7.0"
        const val multiplatformSettings = "0.7.7"

        object Android {
            const val material = "1.2.1"
            const val junit = "4.13.2"
        }

        object AndroidX {
            const val testExt = "1.1.2"
            const val test = "1.3.0"
        }
    }

    object SqlDelight {
        const val driver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val iosDriver = "com.squareup.sqldelight:ios-driver:${Versions.sqlDelight}"
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.Android.material}"
    }

    object AndroidXTest {
        const val core = "androidx.test:core:${Versions.AndroidX.test}"
        const val junit = "androidx.test.ext:junit:${Versions.AndroidX.testExt}"
    }

    object KotlinTest {
        const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val annotationTest =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
        const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val clientMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }

    object Test {
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    }

    object AndroidTest {
        const val junit = "junit:junit:${Versions.Android.junit}"
    }

//    object Moko {
//        const val resourcesGenerator = "dev.icerock.moko:resources-generator:${Versions.mokoResources}"
//        const val resources = "dev.icerock.moko:resources:${Versions.mokoResources}"
//    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object BuildKonfig {
        const val konfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.konfig}"
    }

    object MultiPlatformSettings {
        const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
        const val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    }
}
