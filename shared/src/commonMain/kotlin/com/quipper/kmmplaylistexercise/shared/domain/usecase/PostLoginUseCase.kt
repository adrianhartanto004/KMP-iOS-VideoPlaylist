package com.quipper.kmmplaylistexercise.shared.domain.usecase

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain

interface PostLoginUseCase {
    suspend fun execute(email: String, password: String): LoginDomain
}