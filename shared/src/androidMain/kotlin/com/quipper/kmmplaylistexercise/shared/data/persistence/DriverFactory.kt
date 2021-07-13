package com.quipper.kmmplaylistexercise.shared.data.persistence

import android.content.Context
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = context,
            name = "kmmplaylist.db"
        )
    }
}
