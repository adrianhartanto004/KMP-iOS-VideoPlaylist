package com.quipper.kmmplaylistexercise.shared.data.preference

import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences

abstract class KmmPreferenceMock : KmmPreferences {
    override fun setInt(key: String, value: Int) {
        TODO("Stub")
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        TODO("Stub")
    }

    override fun getInt(key: String): Int? {
        TODO("Stub")
    }

    override fun setFloat(key: String, value: Float) {
        TODO("Stub")
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        TODO("Stub")
    }

    override fun getFloat(key: String): Float? {
        TODO("Stub")
    }

    override fun setLong(key: String, value: Long) {
        TODO("Stub")
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        TODO("Stub")
    }

    override fun getLong(key: String): Long? {
        TODO("Stub")
    }

    override fun setString(key: String, value: String) {
        TODO("Stub")
    }

    override fun getString(key: String, defaultValue: String): String {
        TODO("Stub")
    }

    override fun getString(key: String): String? {
        TODO("Stub")
    }

    override fun setBoolean(key: String, value: Boolean) {
        TODO("Stub")
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        TODO("Stub")
    }

    override fun getBoolean(key: String): Boolean? {
        TODO("Stub")
    }

    override fun remove(key: String) {
        TODO("Stub")
    }

    override fun clear() {
        TODO("Stub")
    }

    override fun hasKey(key: String): Boolean {
        TODO("Stub")
    }
}