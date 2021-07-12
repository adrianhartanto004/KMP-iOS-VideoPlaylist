package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.quipper.kmmplaylistexercise.shared.domain.repository.UserRepository

class UserRepositoryImpl(private val kmmPreferences: KmmPreferences) : UserRepository {

    override suspend fun getToken(): String {
        return kmmPreferences.getString("LOGIN_TOKEN_KEY", "")
    }

    override suspend fun clearToken() {
        kmmPreferences.clear()
    }
}
