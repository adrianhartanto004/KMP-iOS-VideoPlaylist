package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.BaseTest
import com.quipper.kmmplaylistexercise.shared.data.constant.Constants
import com.quipper.kmmplaylistexercise.shared.data.preference.KmmPreferenceMock
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.quipper.kmmplaylistexercise.shared.domain.repository.UserRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRepositoryImplTest : BaseTest() {
    private lateinit var sut: UserRepository
    private lateinit var kmmPreferences: KmmPreferences

    companion object {
        const val TOKEN_VALUE = "2ab3sd8gdf2"
    }

    @BeforeTest
    fun setup() {
        kmmPreferences = object : KmmPreferenceMock() {
            val savedString: MutableMap<String, String> = mutableMapOf()
            override fun setString(key: String, value: String) {
                savedString[key] = value
            }

            override fun getString(key: String, defaultValue: String): String {
                return savedString[key] ?: defaultValue
            }

            override fun clear() {
                savedString.clear()
            }
        }
        sut = UserRepositoryImpl(kmmPreferences)
        kmmPreferences.setString(Constants.LOGIN_TOKEN_KEY, TOKEN_VALUE)
    }

    @Test
    fun `Get token after login should return not empty string`() {
        runTest {
            val actualTokenResult = sut.getToken()
            assertEquals(TOKEN_VALUE, actualTokenResult)
        }
    }

    @Test
    fun `Get token after logout should return an empty string`() {
        runTest {
            sut.clearToken()
            val actualTokenResult = sut.getToken()
            assertEquals("", actualTokenResult)
        }
    }
}