package com.quipper.kmmplaylistexercise.shared.data.preferences

expect class KmmPreferenceFactory {
    fun createKmmPreference(prefName: String) : KmmPreferences
}
