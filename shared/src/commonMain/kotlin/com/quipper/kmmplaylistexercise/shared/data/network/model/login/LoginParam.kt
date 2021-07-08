package com.quipper.kmmplaylistexercise.shared.data.network.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginParam(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)