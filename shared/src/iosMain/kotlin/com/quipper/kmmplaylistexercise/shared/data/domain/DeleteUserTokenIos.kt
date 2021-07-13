package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.DeleteUserTokenUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteUserTokenIos : KoinComponent {
    private val deleteUserTokenUseCase: DeleteUserTokenUseCase by inject()
    fun execute() = SuspendWrapper { deleteUserTokenUseCase.execute() }
}