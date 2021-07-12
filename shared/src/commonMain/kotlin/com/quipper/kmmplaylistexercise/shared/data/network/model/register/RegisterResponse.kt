package com.quipper.kmmplaylistexercise.shared.data.network.model.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: String = ""
)