package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.RegisterRepository
import io.ktor.http.*
import io.ktor.utils.io.errors.*

class RegisterRepositoryImpl(
    private val exerciseApi: ExerciseApi
) : RegisterRepository {
    override suspend fun postRegister(
        email: String,
        name: String,
        password: String
    ): RegisterDomain {
        try {
            val response = exerciseApi.postRegister(email, name, password)
            return RegisterDomain(response.isSuccess())
        } catch (throwable: Throwable) {
            if (throwable is IOException) {
                RegisterDomain(false)
            }
        }
        return RegisterDomain()
    }
}
