package com.quipper.kmmplaylistexercise.shared.data.network.model.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterParam(
    val email: String,
    val name: String,
    val password: String
)
