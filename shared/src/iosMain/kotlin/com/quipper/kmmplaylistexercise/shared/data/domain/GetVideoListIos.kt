package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase

class GetVideoListIos(private val getVideoListUseCase: GetVideoListUseCase) {
    fun execute() = SuspendWrapper { getVideoListUseCase.execute() }
}
