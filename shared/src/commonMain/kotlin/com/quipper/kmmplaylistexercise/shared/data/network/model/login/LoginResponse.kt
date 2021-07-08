package com.quipper.kmmplaylistexercise.shared.data.network.model.login


import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token")
    val token: String = "",
    @SerialName("error")
    val error: String = ""
)

//fun LoginResponse.toDomainModel() =
//    LoginDomain(token, error)
