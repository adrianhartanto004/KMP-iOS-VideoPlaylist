package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostRegisterUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostRegisterIos : KoinComponent {
    private val postRegisterUseCase: PostRegisterUseCase by inject()
    fun execute(email: String, name: String, password: String) =
        SuspendWrapper { postRegisterUseCase.execute(email, name, password) }
}
