package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetUserTokenUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserTokenIos : KoinComponent {
    private val getUserTokenUseCase: GetUserTokenUseCase by inject()
    fun execute() = SuspendWrapper { getUserTokenUseCase.execute() }
}
