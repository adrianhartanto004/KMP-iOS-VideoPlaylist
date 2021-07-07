package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.data.repository.VideoPlaylistRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val repositoryModule = module {
    factory<VideoPlaylistRepository> { VideoPlaylistRepositoryImpl(get()) }
}
