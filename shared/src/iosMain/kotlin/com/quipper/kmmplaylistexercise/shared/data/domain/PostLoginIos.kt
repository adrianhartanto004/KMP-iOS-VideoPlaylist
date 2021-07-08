package com.quipper.kmmplaylistexercise.shared.data.domain

import com.quipper.kmmplaylistexercise.shared.SuspendWrapper
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostLoginUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostLoginIos : KoinComponent {
    private val postLoginUseCase: PostLoginUseCase by inject()
    fun execute(email: String, password: String) =
        SuspendWrapper { postLoginUseCase.execute(email, password) }
}