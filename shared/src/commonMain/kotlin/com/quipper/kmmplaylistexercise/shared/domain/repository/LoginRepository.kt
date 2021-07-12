package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain

interface LoginRepository {
    suspend fun postLogin(email: String, password: String): LoginDomain
}
