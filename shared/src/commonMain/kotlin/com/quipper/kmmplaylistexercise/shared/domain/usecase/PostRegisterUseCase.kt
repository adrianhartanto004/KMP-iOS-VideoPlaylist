package com.quipper.kmmplaylistexercise.shared.domain.usecase

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain

interface PostRegisterUseCase {
    suspend fun execute(email: String, name: String, password: String): RegisterDomain
}
