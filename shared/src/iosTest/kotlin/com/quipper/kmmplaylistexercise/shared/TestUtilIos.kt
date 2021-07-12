package com.quipper.kmmplaylistexercise.shared

import co.touchlab.sqliter.DatabaseConfiguration
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferenceFactory
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

internal actual fun testDbConnection(): SqlDriver {
    val schema = AppDatabase.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "learn.db",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}

internal actual fun testKmmPreferences(): KmmPreferences {
    return KmmPreferenceFactory().createKmmPreference("test_preference")
}
