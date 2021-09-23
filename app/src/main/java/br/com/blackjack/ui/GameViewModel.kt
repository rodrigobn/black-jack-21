package br.com.blackjack.ui

import androidx.lifecycle.ViewModel
import br.com.blackjack.data.model.Jogador
import br.com.blackjack.di.TinyDB
import com.hadilq.liveevent.LiveEvent
import kotlinx.android.synthetic.main.message_box.view.*
import java.util.ArrayList

class GameViewModel: ViewModel() {

    companion object {
        const val KEY_JOGADORES = "jogadores"
    }

    var tinyDB = LiveEvent<TinyDB>()

    var jogador = LiveEvent<Jogador>()
    var jogadores = LiveEvent<ArrayList<Jogador>>()

    var pontuacaoRodadaJogador = LiveEvent<Int>()
    var pontuacaoRodadaBanca = LiveEvent<Int>()
    var blackJack = LiveEvent<Int>()

    fun saveJogador(jogador: Jogador){
        //SE FOR UM JOGADOR NOVO
        if (jogador.nome?.let { isJogadorNovo(it) } == false){
            jogadores.value?.add(jogador)
            val list = arrayListOf<Any>()
            for (jogador in jogadores.value!!) {
                list.add(jogador)
            }
            tinyDB.value!!.putListObject(KEY_JOGADORES, list)
            this.jogador.value = jogador
        } else {
            for (i in jogadores.value?.indices!!){
                if (jogadores.value?.get(i)?.nome.toString()
                        .equals(jogador.nome.toString(), ignoreCase = true)){
                    jogadores.value?.set(i, jogador)
                }
            }
        }
    }

    fun isJogadorNovo(nome: String): Boolean {
        for (jogador in jogadores.value!!) {
            if (jogador.nome.toString().toLowerCase() == nome.toLowerCase()) {
                return true
            }
        }
        return false
    }

    fun getJogador(nome: String): Jogador? {
        for (jogador in jogadores.value!!) {
            if (jogador.nome.toString().toLowerCase() == nome.toLowerCase()) {
                return jogador
            }
        }
        return null
    }

    fun getJogadores(): ArrayList<Jogador>{
        return tinyDB.value!!.getListObject(KEY_JOGADORES, Jogador::class.java) as ArrayList<Jogador>
    }
}