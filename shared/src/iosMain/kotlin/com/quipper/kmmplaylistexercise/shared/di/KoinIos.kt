package com.quipper.kmmplaylistexercise.shared.di

import com.quipper.kmmplaylistexercise.shared.data.domain.*
import com.quipper.kmmplaylistexercise.shared.data.persistence.DatabaseFactory
import com.quipper.kmmplaylistexercise.shared.data.preferences.KmmPreferenceFactory
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactory
import com.quipper.kmmplaylistexercise.shared.data.service.KtorClientFactoryImpl
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import kotlin.reflect.KClass

fun startKoin(): KoinApplication = initKoin(
    arrayOf(iosDomainModule)
) {}

private val iosDomainModule = module {
    factory { GetVideoListIos() }
    factory { PostLoginIos() }
    factory { PostRegisterIos() }
    factory { GetUserTokenIos() }
    factory { DeleteUserTokenIos() }
}

actual val platformModule = module {
    single { DatabaseFactory().createDatabase() }
    single { KmmPreferenceFactory().createKmmPreference("kmmplaylist.ios.preferences") }
    factory<KtorClientFactory> { KtorClientFactoryImpl() }
}

fun <T> Koin.getDependency(clazz: KClass<*>): T {
    return get(clazz, null) { parametersOf(clazz.simpleName) } as T
}

fun <T> Koin.get(objCClass: ObjCClass): T? = getOriginalKotlinClass(objCClass)?.let {
    getDependency(it)
}

fun <T> Koin.get(objCProtocol: ObjCProtocol): T? = getOriginalKotlinClass(objCProtocol)?.let {
    getDependency(it)
}
