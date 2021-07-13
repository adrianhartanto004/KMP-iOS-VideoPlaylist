package com.quipper.kmmplaylistexercise

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

internal actual fun testDbConnection(): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(AppDatabase.Schema, app, "kmmplaylist.db")
}
