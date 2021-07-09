package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.cache.VideoQueries
import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDatabaseEntity
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDomainModel
import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import io.ktor.utils.io.errors.*
import org.koin.core.component.KoinComponent

class VideoPlaylistRepositoryImpl(
    private val exerciseApi: ExerciseApi,
    private val videoQueries: VideoQueries
) : VideoPlaylistRepository, KoinComponent {
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
            print(throwable.message)
            if (throwable is IOException) {
                videoList = videoQueries.getAll().executeAsList().map { it.toDomainModel() }
            }
        }
        return videoList
    }

    override suspend fun postLogin(email: String, password: String): LoginDomain {
        var loginDomain = LoginDomain("", "")
        try {
            val login = exerciseApi.postLogin(email, password)
            loginDomain = if (login.token.isEmpty()) {
                LoginDomain("", login.error)
            } else {
                LoginDomain(login.token, "")
            }
        } catch (throwable: Throwable) {
            print(throwable.message)
        }
        return loginDomain
    }

    override suspend fun postRegister(
        email: String,
        name: String,
        password: String
    ): RegisterDomain {
        var registerDomain = RegisterDomain(false)
        try {
            val login = exerciseApi.postRegister(email, name, password)
            print(login.status)
            registerDomain = if (login.status in 200..300) {
                RegisterDomain(true)
            } else {
                RegisterDomain(false)
            }
        } catch (throwable: Throwable) {
            print(throwable.message)
        }
        return registerDomain
    }
}
