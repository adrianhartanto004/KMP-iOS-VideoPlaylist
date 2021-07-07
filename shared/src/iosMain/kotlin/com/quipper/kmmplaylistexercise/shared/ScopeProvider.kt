package com.quipper.kmmplaylistexercise.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob

class ScopeProvider {
    fun getScopeForIos() = MainScope()
}
