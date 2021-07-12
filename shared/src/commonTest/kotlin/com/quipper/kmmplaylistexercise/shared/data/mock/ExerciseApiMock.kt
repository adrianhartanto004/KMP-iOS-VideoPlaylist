package com.quipper.kmmplaylistexercise.shared.data.mock

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.network.model.login.LoginResponse
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoInfo
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import io.ktor.http.*

class ExerciseApiMock : ExerciseApi {

    var isEmptyRequest = false

    override suspend fun getVideos(): VideoListInfo {
        if (isEmptyRequest) {
            return VideoListInfo(listOf())
        } else {
            return VideoListInfo(
                listOf(
                    VideoInfo(1, "desc", "videoUrl", "author", "thumbnailUrl", "title"),
                    VideoInfo(2, "desc", "videoUrl", "author", "thumbnailUrl", "title"),
                    VideoInfo(3, "desc", "videoUrl", "author", "thumbnailUrl", "title")
                )
            )
        }
    }

    override suspend fun postLogin(email: String, password: String): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun postRegister(
        email: String,
        name: String,
        password: String
    ): HttpStatusCode {
        TODO("Not yet implemented")
    }

}
