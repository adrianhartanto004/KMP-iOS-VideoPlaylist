package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.DeleteUserTokenUseCase

class DeleteUserTokenUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    DeleteUserTokenUseCase {
    override suspend fun execute() {
        return videoPlaylistRepository.clearToken()
    }
}