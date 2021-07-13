package com.quipper.kmmplaylistexercise.shared.domain.usecase

interface GetUserTokenUseCase {
    suspend fun execute(): String
}