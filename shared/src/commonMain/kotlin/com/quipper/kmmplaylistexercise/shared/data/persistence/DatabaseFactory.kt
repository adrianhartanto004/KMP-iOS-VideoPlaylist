package com.quipper.kmmplaylistexercise.shared.data.persistence

import com.quipper.kmmplaylistexercise.shared.cache.AppDatabase

expect class DatabaseFactory {
    fun createDatabase(): AppDatabase
}
