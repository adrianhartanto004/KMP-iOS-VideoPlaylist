package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { get<AppDatabase>().videoQueries }
}
