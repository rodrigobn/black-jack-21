package br.com.blackjack.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import br.com.blackjack.BuildConfig
import br.com.blackjack.data.repository.local.pref.PreferencesHelper
import br.com.blackjack.data.repository.local.pref.PreferencesHelperImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { provideSharedPreferences(androidApplication()) }
    single { providePreferencesHelper(get()) }
}

fun provideSharedPreferences(application: Application): SharedPreferences {
    val masterKeyAlias = MasterKey
        .Builder(application.applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    return EncryptedSharedPreferences.create(
        application.applicationContext,
        BuildConfig.PREFERENCES_FILENAME, masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun providePreferencesHelper(appPref: SharedPreferences): PreferencesHelper {
    return PreferencesHelperImpl(appPref)
}