package com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist

import com.quipper.kmmplaylistexercise.shared.persistence.Video
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
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

fun VideoInfo.toDatabaseEntity() =
    Video(id = id.toLong(), description, videoUrl, author, thumbnailUrl, title)

fun VideoInfo.toDomainModel() =
    VideoDomain(id, description, videoUrl, author, thumbnailUrl, title)

fun Video.toDomainModel() =
    VideoDomain(id.toInt(), description, videoUrl, author, thumbnailUrl, title)

