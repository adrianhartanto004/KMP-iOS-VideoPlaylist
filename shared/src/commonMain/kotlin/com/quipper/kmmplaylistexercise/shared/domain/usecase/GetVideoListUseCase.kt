package com.quipper.kmmplaylistexercise.shared.domain.usecase

import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.VideoListInfo

interface GetVideoListUseCase {
    suspend fun execute(): VideoListInfo
}
