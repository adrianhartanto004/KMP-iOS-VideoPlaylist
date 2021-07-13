package com.quipper.kmmplaylistexercise.shared.domain.usecase.impl

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.repository.VideoPlaylistRepositoryImpl
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.domain.usecase.GetVideoListUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.test.*

//class GetVideoListUseCaseImplTest {
//    private lateinit var sut: GetVideoListUseCase
//    private lateinit var videoPlaylistRepositoryMock: VideoPlaylistRepository
//    private lateinit var exerciseApi: ExerciseApi
//
//    @BeforeTest
//    fun setup() {
//        videoPlaylistRepositoryMock = VideoPlaylistRepositoryImpl(
//            exerciseApi,
//            videoQueries
//        )
//        sut = GetVideoListUseCaseImpl(
//            videoPlaylistRepositoryMock
//        )
//    }
//
//    @DelicateCoroutinesApi
//    @Test
//    fun successfulLogin() {
//        GlobalScope.launch(Dispatchers.Main) {
//            val videoList = sut.execute()
//        }
//    }
//}
