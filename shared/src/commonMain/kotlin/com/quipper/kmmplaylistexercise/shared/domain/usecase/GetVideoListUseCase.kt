package com.quipper.kmmplaylistexercise.shared.domain.usecase

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain

interface GetVideoListUseCase {
    suspend fun execute(): List<VideoDomain>
}
