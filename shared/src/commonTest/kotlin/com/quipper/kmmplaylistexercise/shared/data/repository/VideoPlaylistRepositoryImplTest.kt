package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.BaseTest
import com.quipper.kmmplaylistexercise.shared.KtorClientFactoryTest
import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.persistence.AppDatabase
import com.quipper.kmmplaylistexercise.shared.persistence.VideoQueries
import com.quipper.kmmplaylistexercise.shared.testDbConnection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.test.BeforeTest
import kotlin.test.Test

class VideoPlaylistRepositoryImplTest : BaseTest() {
    private lateinit var videoPlaylistRepository: VideoPlaylistRepository
    private lateinit var videoQueries: VideoQueries
    private lateinit var ktorClientFactory: KtorClientFactoryTest
    private lateinit var exerciseApi: ExerciseApi

    @BeforeTest
    fun setup() {
        ktorClientFactory = KtorClientFactoryTest()
        exerciseApi = ExerciseApi(ktorClientFactory)
        val db = AppDatabase(testDbConnection())
        videoQueries = db.videoQueries
//        videoQueries.clearAll()
        videoPlaylistRepository = VideoPlaylistRepositoryImpl(
            exerciseApi,
            videoQueries
        )
    }

    @DelicateCoroutinesApi
    @Test
    fun `testbro`() = runTest {
        val responseNya = videoPlaylistRepository.getVideos()
        println("responseNya : ${responseNya.get(0)}")
    }

//    @Test
//    fun `fetch data should return data if success`() {
//        KtorClientFactoryTest.respondHandler = { request ->
//            when (request.url.toString()) {
//                "https://api-develop.quipper.net/qlearn/v1/featured_topics" -> {
//                    val content = "[{" +
//                            "\"id\":\"$expectedId\"," +
//                            "\"name\":\"$expectedName\"," +
//                            "\"topics\":[]," +
//                            "\"period_end\":\"${null}\"" +
//                            "}]"
//                    val responseHeaders =
//                        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
//                    respond(
//                        content = content,
//                        headers = responseHeaders
//                    )
//                }
//                else -> error("Unhandled ${request.url}")
//            }
//        }
//        val response = runBlocking {
//            kotlin.runCatching {
//                featuredTopicRepository.fetchFeaturedTopic()
//            }
//        }
//
//        assertTrue(response.isSuccess)
//        assertNotNull(response.getOrNull())
//
//        val featuredTopicInDb = featuredTopicQueries.getAll().executeAsOneOrNull()
//        assertNotNull(featuredTopicInDb)
//        assertTrue {
//            expectedId == featuredTopicInDb.id
//                    && expectedName == featuredTopicInDb.name
//        }
//    }

//    @Test
//    fun `Fetch data should throw error if request failed`() {
//        KtorClientFactoryTest.respondHandler = { request ->
//            when (request.url.toString()) {
//                "https://api-develop.quipper.net/qlearn/v1/featured_topics" -> {
//                    error("Unhandled ${request.url}")
//                }
//                else -> error("Unhandled ${request.url}")
//            }
//        }
//        val response = runBlocking {
//            kotlin.runCatching {
//                featuredTopicRepository.fetchFeaturedTopic()
//            }
//        }
//        assertTrue(response.isFailure)
//        assertNotNull(response.exceptionOrNull())
//    }
}
