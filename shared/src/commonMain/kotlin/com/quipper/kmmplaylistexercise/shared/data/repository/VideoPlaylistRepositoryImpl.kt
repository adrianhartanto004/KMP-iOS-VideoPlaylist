package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.cache.VideoQueries
import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.network.model.login.toDomainModel
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDatabaseEntity
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDomainModel
import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import io.ktor.utils.io.errors.*

class VideoPlaylistRepositoryImpl(
    private val exerciseApi: ExerciseApi,
    private val videoQueries: VideoQueries
) : VideoPlaylistRepository {
    override suspend fun getVideos(): List<VideoDomain> {
        var videoList = listOf<VideoDomain>()
        try {
            val videos = exerciseApi.getVideos().videos
            videos
                .map { it.toDatabaseEntity() }
                .let { videoItem ->
                    videoItem.forEach {
                        videoQueries.insertOrReplaceVideo(it)
                    }
                }
            videoList = videos.map { it.toDomainModel() }
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                videoList = videoQueries.getAll().executeAsList().map { it.toDomainModel() }
            }
        }
        return videoList
    }

    override suspend fun postLogin(email: String, password: String): LoginDomain {
        return exerciseApi.postLogin(email, password).toDomainModel()
    }

    override suspend fun postRegister(
        email: String,
        name: String,
        password: String
    ): RegisterDomain {
        val response = exerciseApi.postRegister(email, name, password)
        return RegisterDomain(response.value in 200..299)
    }
}
