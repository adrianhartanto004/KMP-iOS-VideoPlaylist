package com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoInfo(
    val id: Int,
    val description: String,
    @SerialName("video_url")
    val videoUrl: String,
    val author: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    val title: String
)
