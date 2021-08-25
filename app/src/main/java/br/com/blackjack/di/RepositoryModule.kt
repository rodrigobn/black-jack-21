package br.com.blackjack.di

import br.com.blackjack.data.repository.Repository
import br.com.blackjack.data.repository.RepositoryImpl
import br.com.blackjack.data.repository.local.pref.PreferencesHelper
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepository(get()) }
}

fun provideRepository(
    appPref: PreferencesHelper
): Repository {
    return RepositoryImpl(appPref)
}