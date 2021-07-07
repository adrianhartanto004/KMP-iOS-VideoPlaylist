package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase

class GetVideoListUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) : GetVideoListUseCase {
    override suspend fun execute(): VideoListInfo {
        return videoPlaylistRepository.getVideos()
    }
}
