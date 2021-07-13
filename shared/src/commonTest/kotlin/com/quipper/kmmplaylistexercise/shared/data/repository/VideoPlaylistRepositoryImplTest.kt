package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.BaseTest
import com.quipper.kmmplaylistexercise.shared.data.mock.ExerciseApiMock
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDomainModel
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.quipper.kmmplaylistexercise.shared.persistence.VideoQueries
import com.quipper.kmmplaylistexercise.shared.testDbConnection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.test.*

class VideoPlaylistRepositoryImplTest : BaseTest() {
    private lateinit var sut: VideoPlaylistRepository
    private lateinit var videoQueries: VideoQueries

    private var exerciseApiMock = ExerciseApiMock()

//    companion object {
//        val videoDomainMockData = listOf(
//            VideoDomain(1, "desc", "videoUrl", "author", "thumbnailUrl", "title"),
//            VideoDomain(2, "desc", "videoUrl", "author", "thumbnailUrl", "title"),
//            VideoDomain(3, "desc", "videoUrl", "author", "thumbnailUrl", "title")
//        )
//    }

    @BeforeTest
    fun setup() {
//        exerciseApiMock = ExerciseApiMock()
        val db = AppDatabase(testDbConnection())
        videoQueries = db.videoQueries
//        videoQueries.clearAll()
        sut = VideoPlaylistRepositoryImpl(
            exerciseApiMock,
            videoQueries
        )
    }

    @DelicateCoroutinesApi
    @Test
    fun `fetch data should return data if success`() {
        runTest {
            exerciseApiMock.isEmptyRequest = false
            val apiMockResult = exerciseApiMock.getVideos().videos.map { it.toDomainModel() }
            val result = sut.getVideos()
            assertEquals(apiMockResult, result)

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
            exerciseApiMock.isEmptyRequest = true
            exerciseApiMock.getVideos()
            val result = sut.getVideos()
            assertTrue(result.isNullOrEmpty())
        }
    }
}
