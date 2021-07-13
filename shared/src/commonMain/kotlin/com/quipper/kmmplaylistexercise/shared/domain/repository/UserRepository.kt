package com.quipper.kmmplaylistexercise.shared.domain.repository

interface UserRepository {
    suspend fun getToken(): String
    suspend fun clearToken()
}
