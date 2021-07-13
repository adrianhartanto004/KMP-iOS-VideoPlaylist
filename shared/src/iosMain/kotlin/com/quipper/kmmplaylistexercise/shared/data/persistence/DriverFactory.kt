package com.quipper.kmmplaylistexercise.shared.data.persistence

import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = AppDatabase.Schema,
            name = "kmmplaylist.db"
        )
    }
}
