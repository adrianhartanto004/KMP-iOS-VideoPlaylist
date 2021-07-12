package com.quipper.kmmplaylistexercise.shared.data.network.model.register

import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: String = ""
)

fun RegisterResponse.toDomainModel(): RegisterDomain {
    return if (id.isNotBlank()) {
        RegisterDomain(true)
    } else {
        RegisterDomain(false)
    }
}
