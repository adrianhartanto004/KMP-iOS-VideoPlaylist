package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.RegisterRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostRegisterUseCase

class PostRegisterUseCaseImpl(private val registerRepository: RegisterRepository) :
    PostRegisterUseCase {
    override suspend fun execute(email: String, name: String, password: String): RegisterDomain {
        return registerRepository.postRegister(email, name, password)
    }
}
