package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.repository.VideoPlaylistRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import org.koin.dsl.module

val networkModule = module {

    fun provideExerciseApi(httpClient: KtorClientFactory): ExerciseApi = ExerciseApi(httpClient)

    fun provideRepository(
        exerciseApi: ExerciseApi
    ): VideoPlaylistRepository {
        return VideoPlaylistRepositoryImpl(exerciseApi)
    }

    single { provideExerciseApi(get()) }
    single { provideRepository(get()) }

}
