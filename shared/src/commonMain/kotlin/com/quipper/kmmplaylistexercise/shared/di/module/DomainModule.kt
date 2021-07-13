package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.domain.usecase.*
import com.quipper.kmmplaylistexercise.shared.domain.usecase.impl.*
import org.koin.dsl.module

val domainModule = module {
    factory<GetVideoListUseCase> { GetVideoListUseCaseImpl(get()) }
    factory<PostLoginUseCase> { PostLoginUseCaseImpl(get()) }
    factory<PostRegisterUseCase> { PostRegisterUseCaseImpl(get()) }
    factory<GetUserTokenUseCase> { GetUserTokenUseCaseImpl(get()) }
    factory<DeleteUserTokenUseCase> { DeleteUserTokenUseCaseImpl(get()) }
}
