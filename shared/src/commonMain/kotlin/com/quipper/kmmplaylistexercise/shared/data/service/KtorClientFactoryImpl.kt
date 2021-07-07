package com.quipper.kmmplaylistexercise.shared.data.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json

class KtorClientFactoryImpl : KtorClientFactory {

    override fun createClient(): HttpClient {
        val nonStrictJson =
            Json { isLenient = true; ignoreUnknownKeys = true }
        return HttpClient(CIO) {
            expectSuccess = false

            install(JsonFeature) {
                serializer = KotlinxSerializer(nonStrictJson)
            }
        }
    }
}
