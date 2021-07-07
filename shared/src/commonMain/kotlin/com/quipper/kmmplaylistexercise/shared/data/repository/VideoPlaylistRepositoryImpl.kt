package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository

class VideoPlaylistRepositoryImpl(private val exerciseApi: ExerciseApi) : VideoPlaylistRepository {
    override suspend fun getVideos(): VideoListInfo {
        return exerciseApi.getVideos()
    }
}
