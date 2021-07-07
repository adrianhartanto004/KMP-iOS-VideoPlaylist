package com.quipper.kmmplaylistexercise.shared.di

import com.quipper.kmmplaylistexercise.shared.di.module.networkModule
import com.quipper.kmmplaylistexercise.shared.di.module.repositoryModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

@ExperimentalCoroutinesApi
fun initKoin(appModule: Array<Module>, appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        *appModule,
        platformModule,
        *getSharedModules()
    )
}

@ExperimentalCoroutinesApi
private fun getSharedModules(): Array<Module> {
    return arrayOf(
//        databaseModule,
        repositoryModule,
        networkModule
    )
}
