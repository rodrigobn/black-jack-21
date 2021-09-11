package br.com.blackjack.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.blackjack.R
import br.com.blackjack.data.model.Baralho
import br.com.blackjack.databinding.ActivityGameBinding
import java.util.*

class GameActivity : AppCompatActivity() {

    var pontuacao: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val baralho = Baralho().cartas


        binding.imageCard.setOnClickListener {
            val rand = Random()
            if (baralho.size > 0) {
                val r: Int = rand.nextInt(baralho.size)
                binding.imageCard.setImageResource(baralho[r].imagem)
                pontuacao += baralho[r].valor
                binding.textPontuacao.text = pontuacao.toString()
                baralho.removeAt(r)
            } else {
                binding.imageCard.setImageResource(R.drawable.carta_fundo)
            }
        }

    }
}