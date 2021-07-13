package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.LoginRepository
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostLoginUseCase

class PostLoginUseCaseImpl(private val loginRepository: LoginRepository) :
    PostLoginUseCase {
    override suspend fun execute(email: String, password: String): LoginDomain {
        return loginRepository.postLogin(email, password)
    }
}
