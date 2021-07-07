package com.quipper.kmmplaylistexercise.shared.di

import com.quipper.kmmplaylistexercise.shared.data.persistence.DatabaseFactory
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactoryImpl
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseFactory(get()).createDatabase() }
    factory<KtorClientFactory> { KtorClientFactoryImpl() }
}
