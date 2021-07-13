package com.quipper.kmmplaylistexercise.shared.data.network.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token")
    val token: String = "",
    @SerialName("error")
    val error: String = ""
)
