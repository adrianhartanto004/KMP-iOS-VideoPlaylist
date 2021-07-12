package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostRegisterUseCase

class PostRegisterUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    PostRegisterUseCase {
    override suspend fun execute(email: String, name: String, password: String): RegisterDomain {
        return videoPlaylistRepository.postRegister(email, name, password)
    }
}
