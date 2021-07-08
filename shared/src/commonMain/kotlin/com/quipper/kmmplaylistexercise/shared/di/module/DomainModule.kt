package com.quipper.kmmplaylistexercise.shared.di.module

import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase
import com.quipper.kmmplaylistexercise.shared.domain.usecase.PostLoginUseCase
import com.quipper.kmmplaylistexercise.shared.domain.usecase.impl.GetVideoListUseCaseImpl
import com.quipper.kmmplaylistexercise.shared.domain.usecase.impl.PostLoginUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetVideoListUseCase> { GetVideoListUseCaseImpl(get()) }
    factory<PostLoginUseCase> { PostLoginUseCaseImpl(get()) }
}
