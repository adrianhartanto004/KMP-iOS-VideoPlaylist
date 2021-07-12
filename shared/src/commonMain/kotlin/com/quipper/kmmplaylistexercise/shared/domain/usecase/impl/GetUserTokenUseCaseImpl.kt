package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetUserTokenUseCase

class GetUserTokenUseCaseImpl(private val videoPlaylistRepository: VideoPlaylistRepository) :
    GetUserTokenUseCase {
    override suspend fun execute(): String {
        return videoPlaylistRepository.getToken()
    }
}