package com.quipper.kmmplaylistexercise.shared

import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

class KtorClientFactoryTest : KtorClientFactory {

    @ThreadLocal
    companion object {
        var respondHandler: MockRequestHandler = {
            val responseHeaders =
                headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
            respond(
                "{}",
                headers = responseHeaders
            )
        }
    }

    override fun createClient(): HttpClient {
        val nonStrictJson =
            Json { isLenient = true; ignoreUnknownKeys = true }
        return HttpClient(MockEngine) {
            install(JsonFeature) {
                accept(ContentType.Text.Plain)
                serializer = KotlinxSerializer(nonStrictJson)
            }
        }
    }
}
