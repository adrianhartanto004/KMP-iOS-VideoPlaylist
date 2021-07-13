package com.quipper.kmmplaylistexercise.shared.data.service

import io.ktor.client.*

interface KtorClientFactory {
    fun createClient(): HttpClient
}
