package com.quipper.kmmplaylistexercise.shared.data.persistence

import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase

actual class DatabaseFactory {
    actual fun createDatabase(): AppDatabase {
        val driver = DriverFactory().createDriver()
        return AppDatabase(driver)
    }
}
