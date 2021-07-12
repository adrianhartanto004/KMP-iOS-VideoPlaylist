package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.repository.UserRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.DeleteUserTokenUseCase

class DeleteUserTokenUseCaseImpl(private val userRepository: UserRepository) :
    DeleteUserTokenUseCase {
    override suspend fun execute() {
        return userRepository.clearToken()
    }
}
