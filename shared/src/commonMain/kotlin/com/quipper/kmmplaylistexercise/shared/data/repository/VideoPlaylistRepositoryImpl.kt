package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.data.network.api.ExerciseApi
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDatabaseEntity
import com.quipper.kmmplaylistexercise.shared.data.network.model.videoplaylist.toDomainModel
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.VideoPlaylistRepository
import com.quipper.kmmplaylistexercise.shared.persistence.VideoQueries
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VideoPlaylistRepositoryImpl(
    private val exerciseApi: ExerciseApi,
    private val videoQueries: VideoQueries
) : VideoPlaylistRepository {
    override fun getVideos(): Flow<List<VideoDomain>> {
        return flow {
            val videos = exerciseApi.getVideos().videos
            videos
                .map { it.toDatabaseEntity() }
                .let { videoItem ->
                    videoItem.forEach {
                        videoQueries.insertOrReplaceVideo(it)
                    }
                }
            emit(videos.map { it.toDomainModel() })
        }.catch { exception ->
            if (exception is IOException) {
                emit(videoQueries.getAll().executeAsList().map { it.toDomainModel() })
            }
        }
    }
}
