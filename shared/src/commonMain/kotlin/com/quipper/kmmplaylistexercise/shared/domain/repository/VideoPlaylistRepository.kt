package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo

interface VideoPlaylistRepository {
    suspend fun getVideos(): VideoListInfo
}
