package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    private val exerciseApi: ExerciseApi
) : RegisterRepository {
    override suspend fun postRegister(
        email: String,
        name: String,
        password: String
    ): RegisterDomain {
        val response = exerciseApi.postRegister(email, name, password)
        return RegisterDomain(response.value in 200..299)
    }
}
