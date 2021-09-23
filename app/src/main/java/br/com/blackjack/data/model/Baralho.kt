package br.com.blackjack.data.model

import br.com.blackjack.R
import java.util.*
import kotlin.collections.ArrayList

class Baralho {
    var cartas: MutableList<Carta> = mutableListOf()

    init {
        criaBaralho()
    }

    private fun criaBaralho(){
        adicionaCopas()
        adicionaEspadas()
        adicionaOuro()
        adicionaPaus()

        embaralhar(cartas as ArrayList<Carta>, 52)
    }

    private fun adicionaCopas() {
        cartas.add(Carta(Carta.Naipes.COPAS, 1, R.drawable.a_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 2, R.drawable.dois_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 3, R.drawable.tres_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 4, R.drawable.quatro_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 5, R.drawable.cinco_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 6, R.drawable.seis_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 7, R.drawable.sete_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 8, R.drawable.oito_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 9, R.drawable.nove_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 10, R.drawable.dez_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 10, R.drawable.j11_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 10, R.drawable.q12_copas))
        cartas.add(Carta(Carta.Naipes.COPAS, 10, R.drawable.k13_copas))
    }

    private fun adicionaEspadas() {
        cartas.add(Carta(Carta.Naipes.ESPADAS, 1, R.drawable.a_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 2, R.drawable.dois_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 3, R.drawable.tres_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 4, R.drawable.quatro_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 5, R.drawable.cinco_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 6, R.drawable.seis_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 7, R.drawable.sete_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 8, R.drawable.oito_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 9, R.drawable.nove_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 10, R.drawable.dez_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 10, R.drawable.j11_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 10, R.drawable.q12_espadas))
        cartas.add(Carta(Carta.Naipes.ESPADAS, 10, R.drawable.k13_espadas))
    }

    private fun adicionaOuro() {
        cartas.add(Carta(Carta.Naipes.OURO, 1, R.drawable.a_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 2, R.drawable.dois_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 3, R.drawable.tres_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 4, R.drawable.quatro_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 5, R.drawable.cinco_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 6, R.drawable.seis_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 7, R.drawable.sete_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 8, R.drawable.oito_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 9, R.drawable.nove_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 10, R.drawable.dez_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 10, R.drawable.j11_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 10, R.drawable.q12_ouro))
        cartas.add(Carta(Carta.Naipes.OURO, 10, R.drawable.k13_ouro))
    }

    private fun adicionaPaus() {
        cartas.add(Carta(Carta.Naipes.PAUS, 1, R.drawable.a_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 2, R.drawable.dois_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 3, R.drawable.tres_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 4, R.drawable.quatro_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 5, R.drawable.cinco_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 6, R.drawable.seis_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 7, R.drawable.seis_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 8, R.drawable.oito_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 9, R.drawable.nove_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 10, R.drawable.dez_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 10, R.drawable.j11_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 10, R.drawable.q12_paus))
        cartas.add(Carta(Carta.Naipes.PAUS, 10, R.drawable.k13_paus))
    }

    private fun embaralhar(cartas: ArrayList<Carta>, n: Int) {
        val rand = Random()
        for (i in 0 until n) {
            // Aleatório para as posições restantes.
            val r: Int = i + rand.nextInt(52 - i)

            //trocando os elementos
            val temp = cartas[r]
            cartas[r] = cartas[i]
            cartas[i] = temp
        }
    }
}