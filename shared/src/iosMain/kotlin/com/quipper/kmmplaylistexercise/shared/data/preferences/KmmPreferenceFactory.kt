package com.quipper.kmmplaylistexercise.shared.data.preferences

actual class KmmPreferenceFactory {
    actual fun createKmmPreference(prefName: String): KmmPreferences {
        return KmmPreferencesImpl(prefName)
    }
}
