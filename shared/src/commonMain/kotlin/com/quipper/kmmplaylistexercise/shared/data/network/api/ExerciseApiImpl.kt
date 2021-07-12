package com.quipper.kmmplaylistexercise.shared.data.network.api

import com.quipper.kmmplaylistexercise.shared.data.network.model.login.LoginParam
import com.quipper.kmmplaylistexercise.shared.data.network.model.login.LoginResponse
import com.quipper.kmmplaylistexercise.shared.data.network.model.register.RegisterParam
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ExerciseApiImpl(private val ktorClientFactory: KtorClientFactory) : ExerciseApi {
    override suspend fun getVideos(): VideoListInfo =
        ktorClientFactory.createClient().use {
            val data =
                it.get<String>("https://gist.githubusercontent.com/ayinozendy/a1f7629d8760c0d9cd4a5a4f051d111c/raw/6ead19b28382af688e8b4426d2310f0468a2fb5f/playlist.json")
            return Json { ignoreUnknownKeys = true }.decodeFromString(data)
        }

    override suspend fun postLogin(email: String, password: String): LoginResponse =
        ktorClientFactory.createClient().use {
            return it.post("https://reqres.in/api/login") {
                contentType(ContentType.Application.Json)
                body = LoginParam(email, password)
            }
        }

    override suspend fun postRegister(email: String, name: String, password: String): HttpStatusCode =
        ktorClientFactory.createClient().use {
            return it.post("https://reqres.in/api/users") {
                contentType(ContentType.Application.Json)
                body = RegisterParam(email, name, password)
            }
        }

}
