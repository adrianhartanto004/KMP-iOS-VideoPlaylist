package com.quipper.kmmplaylistexercise.shared.data.persistence

import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase

expect class DatabaseFactory {
    fun createDatabase(): AppDatabase
}
