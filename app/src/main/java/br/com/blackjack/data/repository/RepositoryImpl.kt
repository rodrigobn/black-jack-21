package br.com.blackjack.data.repository

import br.com.blackjack.data.repository.local.pref.PreferencesHelper

class RepositoryImpl(
    private val appPref: PreferencesHelper,
) : Repository {
}