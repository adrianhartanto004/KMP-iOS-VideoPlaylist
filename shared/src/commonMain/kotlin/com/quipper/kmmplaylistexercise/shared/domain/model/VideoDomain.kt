package com.quipper.kmmplaylistexercise.shared.domain.model

data class VideoDomain(
    val id: Int,
    val desc: String,
    val videoUrl: String,
    val author: String,
    val thumbnailUrl: String,
    val title: String
)
