package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.repository.LoginRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.data.repository.RegisterRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.data.repository.UserRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.data.repository.VideoPlaylistRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import com.quipper.kmmplaylistexercise.shared.domain.repository.LoginRepository
import com.quipper.kmmplaylistexercise.shared.domain.repository.RegisterRepository
import com.quipper.kmmplaylistexercise.shared.domain.repository.UserRepository
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideExerciseApi(httpClient: KtorClientFactory): ExerciseApi = ExerciseApi(httpClient)
    single { provideExerciseApi(get()) }
    factory<VideoPlaylistRepository> { VideoPlaylistRepositoryImpl(get(), get()) }
    factory<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    factory<RegisterRepository> { RegisterRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
}
