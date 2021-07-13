package com.quipper.kmmplaylistexercise

import com.quipper.kmmplaylistexercise.shared.Platform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
