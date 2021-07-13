package com.quipper.kmmplaylistexercise.shared.data.preferences

import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import platform.Foundation.NSUserDefaults

class KmmPreferencesImpl(preferenceName: String) : KmmPreferences {
    private val nsUserDefaults: NSUserDefaults = NSUserDefaults(suiteName = preferenceName)

    override fun setInt(key: String, value: Int) {
        return nsUserDefaults.setInteger(value.toLong(), key)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return if (hasKey(key))
            nsUserDefaults.integerForKey(key).toInt()
        else
            defaultValue
    }

    override fun getInt(key: String): Int? {
        return if (hasKey(key))
            nsUserDefaults.integerForKey(key).toInt()
        else
            null
    }

    override fun setFloat(key: String, value: Float) {
        return nsUserDefaults.setFloat(value, key)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return if (hasKey(key))
            nsUserDefaults.floatForKey(key)
        else
            defaultValue
    }

    override fun getFloat(key: String): Float? {
        return if (hasKey(key))
            nsUserDefaults.floatForKey(key)
        else
            null
    }

    override fun setLong(key: String, value: Long) {
        return nsUserDefaults.setInteger(value, key)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return if (hasKey(key))
            nsUserDefaults.integerForKey(key)
        else defaultValue
    }

    override fun getLong(key: String): Long? {
        return if (hasKey(key))
            nsUserDefaults.integerForKey(key)
        else
            null
    }

    override fun setString(key: String, value: String) {
        return nsUserDefaults.setObject(value, key)
    }

    override fun getString(key: String, defaultValue: String): String {
        return nsUserDefaults.stringForKey(key) ?: defaultValue
    }

    override fun getString(key: String): String? {
        return if (hasKey(key))
            nsUserDefaults.stringForKey(key) ?: ""
        else
            null
    }

    override fun setBoolean(key: String, value: Boolean) {
        return nsUserDefaults.setBool(value, key)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (hasKey(key))
            nsUserDefaults.boolForKey(key)
        else
            defaultValue
    }

    override fun getBoolean(key: String): Boolean? {
        return if (hasKey(key))
            nsUserDefaults.boolForKey(key)
        else
            null
    }

    override fun remove(key: String) {
        nsUserDefaults.removeObjectForKey(key)
    }

    override fun clear() {
        nsUserDefaults.dictionaryRepresentation().keys.forEach { key ->
            remove(key as String)
        }
    }

    override fun hasKey(key: String): Boolean {
        return nsUserDefaults.objectForKey(key) != null
    }
}
