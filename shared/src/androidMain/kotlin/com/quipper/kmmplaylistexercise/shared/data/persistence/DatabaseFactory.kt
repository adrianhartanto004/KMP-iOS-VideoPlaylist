package com.quipper.kmmplaylistexercise.shared.data.persistence

import android.content.Context
import com.quipper.kmmplaylistexercise.shared.cache.AppDatabase

actual class DatabaseFactory(private val context: Context) {
    actual fun createDatabase(): AppDatabase {
        val driver = DriverFactory(context).createDriver()
        return AppDatabase(driver)
    }
}
