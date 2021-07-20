package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import kotlinx.coroutines.flow.Flow

interface VideoPlaylistRepository {
    fun getVideos(): Flow<List<VideoDomain>>
}
