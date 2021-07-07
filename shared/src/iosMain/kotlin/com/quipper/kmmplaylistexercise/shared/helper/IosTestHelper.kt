package com.quipper.kmmplaylistexercise.shared.helper

import kotlin.native.concurrent.freeze

@Throws(Throwable::class)
fun rethrow(exception: Throwable): Nothing {
    exception.freeze()
    throw exception
}

fun <T> freeze(array: List<T>) {
    array.forEach {
        it.freeze()
    }
}
