package com.quipper.kmmplaylistexercise.shared.data.persistence

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}
