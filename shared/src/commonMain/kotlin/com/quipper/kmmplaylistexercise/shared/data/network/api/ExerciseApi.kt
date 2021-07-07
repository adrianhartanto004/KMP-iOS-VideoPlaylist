package com.quipper.kmmplaylistexercise.shared.data.network.api

import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class ExerciseApi(private val ktorClientFactory: KtorClientFactory) {
    suspend fun getVideos(): VideoListInfo =
        ktorClientFactory.createClient().use {
            it.get("https://gist.githubusercontent.com/ayinozendy/a1f7629d8760c0d9cd4a5a4f051d111c/raw/6ead19b28382af688e8b4426d2310f0468a2fb5f/playlist.json")
        }
}
