package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostLoginUseCase

class PostLoginUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    PostLoginUseCase {
    override suspend fun execute(email: String, password: String): LoginDomain? {
        return videoPlaylistRepository.postLogin(email, password)
    }
}
