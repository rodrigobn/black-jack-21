package br.com.blackjack.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.blackjack.R
import br.com.blackjack.data.model.Baralho
import br.com.blackjack.data.model.Carta
import br.com.blackjack.databinding.ActivityGameBinding
import br.com.blackjack.di.TinyDB
import kotlinx.android.synthetic.main.message_box.view.*
import java.util.*

class GameActivity : AppCompatActivity() {

    companion object {
        private const val KEY_NOME_JOGADOR = "nome_jogador"
        private const val KEY_PONTUACAO_RODADA = "pontuacao_rodada"
        private const val KEY_JOGADORES = "jogadores"
    }

    private var pontuacaoRodada: Int = 0
    private var blackJack: Int = 0
    private var rodada: Int = 0
    private lateinit var tinyDB: TinyDB
    private lateinit var baralho: MutableList<Carta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initView()

        binding.imageBaralho.setOnClickListener {
            val rand = Random()
            if (baralho.size > 0) {
                val r: Int = rand.nextInt(baralho.size)
                binding.imageBaralho.setImageResource(baralho[r].imagem)
                pontuacaoRodada += baralho[r].valor
                binding.textSuaPontuacao.text = pontuacaoRodada.toString()
                baralho.removeAt(r)
            } else {
                binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
            }
        }

    }

    private fun initComponents() {
        tinyDB = TinyDB(this)
        baralho = Baralho().cartas
    }

    private fun initView() {
        dialogInit()
    }

    private fun dialogInit() {
        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.message_box, null)
        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        messageBoxBuilder.setCancelable(false)
        //show dialog
        val messageBoxInstance = messageBoxBuilder.show()
        //set Listener
        messageBoxView.buttonStart.setOnClickListener(){
            //var j = Jogador(10, 1, messageBoxView.edit_text_name.text.toString(), 1)
            //var list = arrayListOf<Any>()
            //list.add(j)
            //tinyDB.putListObject(KEY_JOGADORES, list)
            //close dialog
            messageBoxInstance.dismiss()
            tinyDB.putString(KEY_NOME_JOGADOR, messageBoxView.edit_text_name.text.toString())
        }
    }
}