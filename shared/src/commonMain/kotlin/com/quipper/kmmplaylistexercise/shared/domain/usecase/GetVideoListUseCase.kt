package com.quipper.kmmplaylistexercise.shared.domain.usecase

import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import kotlinx.coroutines.flow.Flow

interface GetVideoListUseCase {
    fun execute(): Flow<List<VideoDomain>>
}
