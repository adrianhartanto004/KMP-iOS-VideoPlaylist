package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain

interface RegisterRepository {
    suspend fun postRegister(email: String, name: String, password: String): RegisterDomain
}
