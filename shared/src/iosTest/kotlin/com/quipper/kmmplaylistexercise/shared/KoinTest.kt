package com.quipper.kmmplaylistexercise.shared

import com.quipper.kmmplaylistexercise.shared.di.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.Test

class KoinTest : BaseTest() {
    @Test
    fun checkAllModules() {
        startKoin()
    }

    @AfterTest
    fun breakdown() {
        stopKoin()
    }
}
