package com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListInfo(
    @SerialName("play_list")
    val videos: List<VideoInfo>
)
