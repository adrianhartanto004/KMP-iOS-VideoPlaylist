package com.quipper.kmmplaylistexercise.shared

import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferences
import com.squareup.sqldelight.db.SqlDriver

internal expect fun testDbConnection(): SqlDriver
internal expect fun testKmmPreferences(): KmmPreferences
