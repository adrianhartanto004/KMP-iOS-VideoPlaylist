package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase
import com.rickclephas.kmp.nativecoroutines.asNativeFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetVideoListIos : KoinComponent {
    private val getVideoListUseCase: GetVideoListUseCase by inject()
    fun execute() = getVideoListUseCase.execute().asNativeFlow()
}
