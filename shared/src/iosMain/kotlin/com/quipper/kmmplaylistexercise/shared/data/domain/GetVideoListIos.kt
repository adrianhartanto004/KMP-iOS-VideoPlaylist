package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVideoListIos : KoinComponent {
    private val getVideoListUseCase: GetVideoListUseCase by inject()
    fun execute() = SuspendWrapper { getVideoListUseCase.execute() }
}
