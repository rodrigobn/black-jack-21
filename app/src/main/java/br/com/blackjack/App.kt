package br.com.blackjack

import android.app.Application
import br.com.blackjack.data.repository.local.pref.AppPreferences
import br.com.blackjack.di.TinyDB
import br.com.blackjack.di.localModule
import br.com.blackjack.di.repositoryModule
import br.com.blackjack.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppPreferences.init(this)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(localModule, repositoryModule, viewModel))
        }
    }
}