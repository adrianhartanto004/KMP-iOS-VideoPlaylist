package com.quipper.kmmplaylistexercise.shared.data.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent

class KtorClientFactoryImpl : KtorClientFactory, KoinComponent {

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
