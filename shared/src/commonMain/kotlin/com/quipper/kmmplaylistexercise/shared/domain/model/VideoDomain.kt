package com.quipper.kmmplaylistexercise.shared.domain.model

data class VideoDomain(
    val id: Int,
    val description: String,
    val videoUrl: String,
    val author: String,
    val thumbnailUrl: String,
    val title: String
)
