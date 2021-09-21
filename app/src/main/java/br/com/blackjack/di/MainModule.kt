package br.com.blackjack.di

import br.com.blackjack.ui.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { GameViewModel() }
}