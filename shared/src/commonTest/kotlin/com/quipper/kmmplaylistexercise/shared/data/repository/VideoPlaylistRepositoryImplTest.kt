package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.BaseTest
import com.quipper.kmmplaylistexercise.shared.data.mock.ExerciseApiMock
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDomainModel
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.quipper.kmmplaylistexercise.shared.persistence.VideoQueries
import com.quipper.kmmplaylistexercise.shared.testDbConnection
import kotlinx.coroutines.flow.collect
import kotlin.test.*

class VideoPlaylistRepositoryImplTest : BaseTest() {
    private lateinit var sut: VideoPlaylistRepository
    private lateinit var videoQueries: VideoQueries

    private var exerciseApiMock = ExerciseApiMock()

    @BeforeTest
    fun setup() {
        val db = AppDatabase(testDbConnection())
        videoQueries = db.videoQueries
        sut = VideoPlaylistRepositoryImpl(
            exerciseApiMock,
            videoQueries
        )
    }

    @Test
    fun `fetch data should return data if success`() {
        runTest {
            exerciseApiMock.isEmptyRequest = false
            var resultList = listOf<VideoDomain>()
            val apiMockResult = exerciseApiMock.getVideos().videos.map { it.toDomainModel() }
            val result = sut.getVideos()
            result.collect {
                resultList = it
            }
            assertEquals(resultList, apiMockResult)

            val videoDb = videoQueries.getAll().executeAsList()
            assertNotNull(videoDb)
            assertTrue {
                apiMockResult[0].id == videoDb[0].id.toInt()
            }
        }
    }

    @Test
    fun `fetch data empty if result isEmpty`() {
        runTest {
            var resultList = listOf<VideoDomain>()
            exerciseApiMock.isEmptyRequest = true
            exerciseApiMock.getVideos()
            val result = sut.getVideos()
            result.collect {
                resultList = it
            }
            assertTrue(resultList.isNullOrEmpty())
        }
    }
}
