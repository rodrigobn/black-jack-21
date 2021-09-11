package br.com.blackjack.data.model

data class Carta(var naipe: Naipes, var valor: Int, var imagem: Int) {

    /**
     * Enumere as diferentes possibilidades de naipes
     */
    enum class Naipes {
        PAUS, OURO, COPAS, ESPADAS
    }

    /**
     * Enumere as diferentes possibilidades de classificações
     */
    enum class Valores {
        A, DOIS, TRES, QUATRO, CINCO, SEIS, SETE,
        OITO, NOVE, DEZ, VALETE, DAMA, REIS
    }
}