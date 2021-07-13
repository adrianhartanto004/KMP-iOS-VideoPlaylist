package com.quipper.kmmplaylistexercise.shared.data.network.api

import com.quipper.kmmplaylistexercise.shared.data.network.model.login.LoginResponse
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import io.ktor.http.*

interface ExerciseApi {
    suspend fun getVideos(): VideoListInfo
    suspend fun postLogin(email: String, password: String): LoginResponse
    suspend fun postRegister(email: String, name: String, password: String): HttpStatusCode
}
