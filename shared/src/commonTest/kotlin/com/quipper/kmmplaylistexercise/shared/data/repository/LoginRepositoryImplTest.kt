package com.quipper.kmmplaylistexercise.shared.data.repository

import com.quipper.kmmplaylistexercise.shared.BaseTest
import com.quipper.kmmplaylistexercise.shared.data.constant.Constants
import com.quipper.kmmplaylistexercise.shared.data.mock.ExerciseApiMock
import com.quipper.kmmplaylistexercise.shared.data.preference.KmmPreferenceMock
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.quipper.kmmplaylistexercise.shared.domain.model.LoginDomain
import com.quipper.kmmplaylistexercise.shared.domain.repository.LoginRepository
import kotlin.test.*

class LoginRepositoryImplTest : BaseTest() {
    private lateinit var sut: LoginRepository
    private lateinit var kmmPreferences: KmmPreferences

    private var exerciseApiMock = ExerciseApiMock()

    companion object {
        const val EMAIL = "email@email.com"
        const val PASSWORD = "password"
    }

    @BeforeTest
    fun setup() {
        kmmPreferences = object : KmmPreferenceMock() {
            val savedString: MutableMap<String, String> = mutableMapOf()
            override fun setString(key: String, value: String) {
                savedString[key] = value
            }

            override fun getString(key: String): String? {
                return savedString[key]
            }

            override fun clear() {
                savedString.clear()
            }
        }
        sut = LoginRepositoryImpl(
            kmmPreferences,
            exerciseApiMock
        )
    }

    @Test
    fun `postLogin success should return token and empty error`() {
        exerciseApiMock.isLoginSuccess = true
        runTest {
            val expectedResult = exerciseApiMock.postLogin(EMAIL, PASSWORD)
            val expectedLoginDomainResult = LoginDomain(expectedResult.token, "")
            val actualResult = sut.postLogin(EMAIL, PASSWORD)
            assertEquals(expectedLoginDomainResult, actualResult)
        }
    }

    @Test
    fun `postLogin success token should not be empty`() {
        exerciseApiMock.isLoginSuccess = true
        runTest {
            sut.postLogin(EMAIL, PASSWORD)
            val kmmPreferenceToken = kmmPreferences.getString(Constants.LOGIN_TOKEN_KEY)
            assertNotNull(kmmPreferenceToken)
        }
    }

    @Test
    fun `postLogin failed token should be empty`() {
        exerciseApiMock.isLoginSuccess = false
        runTest {
            sut.postLogin(EMAIL, PASSWORD)
            val kmmPreferenceToken = kmmPreferences.getString(Constants.LOGIN_TOKEN_KEY)
            assertNull(kmmPreferenceToken)
        }
    }
}