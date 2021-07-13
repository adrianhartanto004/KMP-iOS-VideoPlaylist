package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase

class GetVideoListUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    GetVideoListUseCase {
    override suspend fun execute(): List<VideoDomain> {
        return videoPlaylistRepository.getVideos()
    }
}
