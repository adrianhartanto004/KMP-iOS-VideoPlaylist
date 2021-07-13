package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.constant.Constants
import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.LoginRepository
import io.ktor.utils.io.errors.*

class LoginRepositoryImpl(
    private val kmmPreferences: KmmPreferences,
    private val exerciseApi: ExerciseApi
) : LoginRepository {
    override suspend fun postLogin(email: String, password: String): LoginDomain {
        try {
            val response = exerciseApi.postLogin(email, password)
            return if (response.token.isNotEmpty()) {
                kmmPreferences.setString(Constants.LOGIN_TOKEN_KEY, response.token)
                LoginDomain(response.token, "")
            } else {
                LoginDomain("", response.error)
            }
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                return LoginDomain("", "Network error\nPlease check your connection")
            }
        }
        return LoginDomain()
    }
}
