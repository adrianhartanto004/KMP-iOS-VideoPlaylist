package com.quipper.kmmplaylistexercise.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.native.concurrent.freeze

class SuspendWrapper<T>(private val suspender: suspend () -> T) {

    init {
        freeze()
    }

    fun subscribe(
        scope: CoroutineScope,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Job {
        return scope.launch {
            try {
                onSuccess(suspender.invoke().freeze())
            } catch (error: Throwable) {
                onError(error)
            }
        }.freeze()
    }
}
