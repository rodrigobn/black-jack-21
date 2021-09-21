package br.com.blackjack.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.blackjack.R
import br.com.blackjack.data.model.Baralho
import br.com.blackjack.data.model.Jogador
import br.com.blackjack.databinding.ActivityGameBinding
import br.com.blackjack.di.TinyDB
import kotlinx.android.synthetic.main.message_box.view.*
import java.util.*

class GameActivity : AppCompatActivity() {

    companion object {
        const val KEY_NOME_JOGADOR = "nome_jogador"
        const val KEY_PONTUACAO_RODADA = "pontuacao_rodada"
        const val KEY_JOGADORES = "jogadores"
    }
    private var pontuacaoRodadaJogador: Int = 0
    private var pontuacaoRodadaBanca: Int = 0
    private var blackJack: Int = 0
    private var rodada: Int = 0

    private lateinit var binding: ActivityGameBinding
    private val rand = Random()
/*
    private lateinit var tinyDB: TinyDB
    private lateinit var baralho: MutableList<Carta>
    private lateinit var jogador: Jogador
    private lateinit var jogadores: ArrayList<Jogador>
*/

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initView()

        viewModel.tinyDB.value?.clear()

        binding.buttonMaisUma.setOnClickListener {
            val baralho = viewModel.baralho.value
            if (baralho!!.size > 0) {
                val r: Int = rand.nextInt(baralho.size)
                binding.imageBaralho.setImageResource(baralho[r].imagem)
                when(rodada){
                    0 -> binding.imageCardJogador1.setImageResource(baralho[r].imagem)
                    1 -> binding.imageCardJogador2.setImageResource(baralho[r].imagem)
                    2 -> binding.imageCardJogador3.setImageResource(baralho[r].imagem)
                    3 -> binding.imageCardJogador4.setImageResource(baralho[r].imagem)
                    4 -> binding.imageCardJogador5.setImageResource(baralho[r].imagem)
                }
                pontuacaoRodadaJogador += baralho[r].valor
                viewModel.jogador.value?.pontosRodada = pontuacaoRodadaJogador
                binding.textSuaPontuacao.text = String.format(getString(R.string.pontuacao_jogador), viewModel.jogador.value?.pontosRodada)
                baralho.removeAt(r)
            } else {
                binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
            }
            rodada++
        }

        binding.buttonParar.setOnClickListener {
            rodada = 0
            jogadaBanca()
            //finish()
        }
    }

    private fun jogadaBanca() {
        val baralho = viewModel.baralho.value
        while (pontuacaoRodadaBanca < 21) {
            when(pontuacaoRodadaBanca){
                17 -> return
                18 -> return
                19 -> return
                20 -> return
                21 -> return
            }
            if (baralho!!.size > 0) {
                val r: Int = rand.nextInt(baralho.size)
                binding.imageBaralho.setImageResource(baralho[r].imagem)
                when(rodada){
                    0 -> binding.imageCardBanca1.setImageResource(baralho[r].imagem)
                    1 -> binding.imageCardBanca2.setImageResource(baralho[r].imagem)
                    2 -> binding.imageCardBanca3.setImageResource(baralho[r].imagem)
                    3 -> binding.imageCardBanca4.setImageResource(baralho[r].imagem)
                    4 -> binding.imageCardBanca5.setImageResource(baralho[r].imagem)
                }
                pontuacaoRodadaBanca += baralho[r].valor
                viewModel.pontuacaoRodadaBanca.value = pontuacaoRodadaBanca
                binding.textPontuacaoComputador.text = String.format(getString(R.string.pontuacao_banca), viewModel.pontuacaoRodadaBanca.value)
                baralho.removeAt(r)
            } else {
                binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
            }
            rodada++
        }

    }

    override fun onBackPressed() {

    }

    private fun initComponents() {
        viewModel.tinyDB.value = TinyDB(this)
        viewModel.baralho.value = Baralho().cartas
        viewModel.jogadores.value = viewModel.getJogadores()
        viewModel.jogador.value = Jogador()
    }

    private fun initView() {
        dialogInit()
    }

    private fun dialogInit() {
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.message_box, null)
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        messageBoxBuilder.setCancelable(false)

        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.buttonStart.setOnClickListener(){
            if (!viewModel.isJogadorNovo(messageBoxView.edit_text_name.toString())){
                val novoJogador = Jogador()
                novoJogador.nome = messageBoxView.edit_text_name.text.toString()
                viewModel.saveJogador(novoJogador)
            } else {
                dialogJogadorExiste(viewModel.getJogador(messageBoxView.edit_text_name.toString()))
            }
            messageBoxInstance.dismiss()
        }
    }

    private fun dialogJogadorExiste(jogador: Jogador?){
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setMessage("Continuar com o jogador?")
            .setTitle("Jogador ja existe")

        builder.apply {
            setPositiveButton("Sim",
                DialogInterface.OnClickListener { dialog, id ->
                    viewModel.jogador.value = jogador
                })
            setNegativeButton("Não",
                DialogInterface.OnClickListener { dialog, id ->
                    dialogInit()
                    dialog.dismiss()
                })
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}