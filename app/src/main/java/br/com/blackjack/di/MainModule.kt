package br.com.blackjack.di

import br.com.blackjack.ui.GameViewModel
import br.com.blackjack.ui.jogadores.ListaJogadoresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { GameViewModel() }
    viewModel { ListaJogadoresViewModel() }
}