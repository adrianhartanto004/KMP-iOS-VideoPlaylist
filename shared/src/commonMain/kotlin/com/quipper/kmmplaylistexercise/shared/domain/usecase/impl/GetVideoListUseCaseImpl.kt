package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase
import kotlinx.coroutines.flow.Flow

class GetVideoListUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    GetVideoListUseCase {
    override fun execute(): Flow<List<VideoDomain>> {
        return videoPlaylistRepository.getVideos()
    }
}
