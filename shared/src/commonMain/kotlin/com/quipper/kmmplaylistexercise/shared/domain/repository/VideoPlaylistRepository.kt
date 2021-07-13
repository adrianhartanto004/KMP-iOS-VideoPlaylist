package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain

interface VideoPlaylistRepository {
    suspend fun getVideos(): List<VideoDomain>
}
