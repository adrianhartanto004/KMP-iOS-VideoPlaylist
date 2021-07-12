package com.quipper.kmmplaylistexercise.shared.domain.repository

import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.RegisterDomain
import com.quipper.kmmplaylistexercise.shared.domain.model.VideoDomain

interface VideoPlaylistRepository {
    suspend fun getVideos(): List<VideoDomain>
    suspend fun postLogin(email: String, password: String): LoginDomain
    suspend fun postRegister(email: String, name: String, password: String): RegisterDomain
    suspend fun getToken(): String
}
