package com.quipper.kmmplaylistexercise.shared.data.preferences

import android.app.Application

actual class KmmPreferenceFactory(private val application: Application) {
    actual fun createKmmPreference(prefName: String): KmmPreferences {
        return KmmPreferencesImpl(application, prefName)
    }
}
