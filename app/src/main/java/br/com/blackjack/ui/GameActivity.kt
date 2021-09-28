package br.com.blackjack.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.blackjack.R
import br.com.blackjack.data.model.Baralho
import br.com.blackjack.data.model.Carta
import br.com.blackjack.data.model.Jogador
import br.com.blackjack.databinding.ActivityGameBinding
import br.com.blackjack.di.TinyDB
import br.com.blackjack.ui.GameViewModel.Companion.KEY_JOGADORES
import kotlinx.android.synthetic.main.message_box.view.*
import kotlinx.android.synthetic.main.message_vitoria_black_jack.view.*
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    private var pontuacaoRodadaJogador: Int = 0
    private var pontuacaoRodadaBanca: Int = 0
    private var rodada: Int = 0

    private lateinit var binding: ActivityGameBinding
    private val rand = Random()

    private lateinit var baralho: MutableList<Carta>
    //private lateinit var jogador: Jogador

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initView()

        //LIMPAR
        //viewModel.tinyDB.value?.clear()

        //Lista
        val jogadores = viewModel.getJogadores()
        Log.d("xxx", "nome: ${jogadores}")
        for (jogador in jogadores){
            Log.d("xxx", "nome: ${jogador.nome}")
            Log.d("xxx", "empates: ${jogador.blackJack}")
            Log.d("xxx", "jogos: ${jogador.derrotas}")
            Log.d("xxx", "derrotas: ${jogador.vitorias}")
            Log.d("xxx", "blackJack: ${jogador.empates}")
            Log.d("xxx", "vitorias: ${jogador.jogos}")
            Log.d("xxx", "\n")
        }

        binding.buttonMaisUma.setOnClickListener {
            jogadaJogador()
        }

        binding.buttonParar.setOnClickListener {
            rodada = 0
            jogadaBanca()
        }

        binding.buttonNovoJogo.setOnClickListener {
            resetGame()
        }

        binding.buttonSair.setOnClickListener { finish() }
    }

    private fun jogadaJogador() {
        if (baralho.size > 0) {
            val r: Int = rand.nextInt(baralho.size)
            binding.imageBaralho.setImageResource(baralho[r].imagem)
            when (rodada) {
                0 -> {
                    binding.imageCardJogador1.visibility = VISIBLE
                    binding.imageCardJogador1.setImageResource(baralho[r].imagem)
                }
                1 -> {
                    binding.imageCardJogador2.visibility = VISIBLE
                    binding.imageCardJogador2.setImageResource(baralho[r].imagem)
                }
                2 -> {
                    binding.imageCardJogador3.visibility = VISIBLE
                    binding.imageCardJogador3.setImageResource(baralho[r].imagem)
                }
                3 -> {
                    binding.imageCardJogador4.visibility = VISIBLE
                    binding.imageCardJogador4.setImageResource(baralho[r].imagem)
                }
                4 -> {
                    binding.imageCardJogador5.visibility = VISIBLE
                    binding.imageCardJogador5.setImageResource(baralho[r].imagem)
                }
            }
            // Verifica o melhor valor para o As
            if (baralho[r].valor == 1){
                if (pontuacaoRodadaJogador + 11 <= 21){
                    pontuacaoRodadaJogador += 11
                } else {
                    pontuacaoRodadaJogador += baralho[r].valor
                }
            } else {
                pontuacaoRodadaJogador += baralho[r].valor
            }
            //DERROTA POR PASSAR DE @!
            if (pontuacaoRodadaJogador > 21){
                dialogVitoriaBanca()
            }
            binding.textSuaPontuacao.text = String.format(getString(R.string.pontuacao_jogador),pontuacaoRodadaJogador)
            baralho.removeAt(r)
        } else {
            binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
        }
        if (pontuacaoRodadaJogador == 21){
            dialogVitoriaPorBlackJack()
        }
        rodada++
    }

    private fun jogadaBanca() {
        while (pontuacaoRodadaBanca <= 21 && pontuacaoRodadaBanca < pontuacaoRodadaJogador) {
            if (baralho.size > 0) {
                val r: Int = rand.nextInt(baralho.size)
                binding.imageBaralho.setImageResource(baralho[r].imagem)
                when(rodada){
                    0 -> {
                        binding.imageCardBanca1.visibility = VISIBLE
                        binding.imageCardBanca1.setImageResource(baralho[r].imagem)
                    }
                    1 -> {
                        binding.imageCardBanca2.visibility = VISIBLE
                        binding.imageCardBanca2.setImageResource(baralho[r].imagem)
                    }
                    2 -> {
                        binding.imageCardBanca3.visibility = VISIBLE
                        binding.imageCardBanca3.setImageResource(baralho[r].imagem)
                    }
                    3 -> {
                        binding.imageCardBanca4.visibility = VISIBLE
                        binding.imageCardBanca4.setImageResource(baralho[r].imagem)
                    }
                    4 -> {
                        binding.imageCardBanca5.visibility = VISIBLE
                        binding.imageCardBanca5.setImageResource(baralho[r].imagem)
                    }
                }
                // Verifica o melhor valor para o As
                if (baralho[r].valor == 1){
                    if (pontuacaoRodadaBanca + 11 <= 21){
                        pontuacaoRodadaBanca += 11
                    } else {
                        pontuacaoRodadaBanca += baralho[r].valor
                    }
                } else {
                    pontuacaoRodadaBanca += baralho[r].valor
                }
                binding.textPontuacaoComputador.text = String.format(getString(R.string.pontuacao_banca), pontuacaoRodadaBanca)
                baralho.removeAt(r)
            } else {
                binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
            }
            rodada++
        }

        if (pontuacaoRodadaBanca == 21){
            dialogVitoriaBanca()
            return
        }

        if (pontuacaoRodadaBanca == pontuacaoRodadaJogador){
            dialogEmpate()
            return
        }

        if (pontuacaoRodadaBanca > 21){
            dialogVitoriaNormal()
            return
        }
        if (pontuacaoRodadaJogador > pontuacaoRodadaBanca){
            dialogVitoriaNormal()
            return
        }
        if (pontuacaoRodadaJogador < pontuacaoRodadaBanca){
            dialogVitoriaBanca()
            return
        }
    }

    override fun onBackPressed() {

    }

    private fun initComponents() {
        baralho = Baralho().cartas
        viewModel.tinyDB.value = TinyDB(this)
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
            if (messageBoxView.edit_text_name.text.isNullOrEmpty()){
                return@setOnClickListener
            }
            if (viewModel.isJogadorNovo(messageBoxView.edit_text_name.text.toString())){
                viewModel.jogador.value?.nome = messageBoxView.edit_text_name.text.toString()
                viewModel.saveJogador(viewModel.jogador.value!!)
            } else {
                dialogJogadorExiste(viewModel.getJogador(messageBoxView.edit_text_name.text.toString()))
            }
            messageBoxInstance.dismiss()
        }
    }

    private fun dialogJogadorExiste(jogador: Jogador?){
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setCancelable(false)
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

    private fun dialogVitoriaPorBlackJack() {

        viewModel.jogador.value!!.blackJack = viewModel.jogador.value!!.blackJack?.plus(1)

        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.message_vitoria_black_jack, null)
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        messageBoxBuilder.setCancelable(false)

        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.buttonJogarNovamente.setOnClickListener(){
            vitoriaJogador()
            resetGame()
            messageBoxInstance.dismiss()
        }

        messageBoxView.buttonSair.setOnClickListener(){
            vitoriaJogador()
            finish()
            messageBoxInstance.dismiss()
        }
    }

    private fun dialogVitoriaNormal() {
        vitoriaJogador()

        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setCancelable(false)
        builder.setMessage("Voce ganhou")
            .setTitle("Parabéns!")

        builder.apply {
            setPositiveButton("Novo jogo",
                DialogInterface.OnClickListener { dialog, id ->
                    resetGame()
                    dialog.dismiss()
                })
            setNegativeButton("Sair",
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                    dialog.dismiss()
                })
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun dialogVitoriaBanca() {
        vitoriaBanca()

        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setCancelable(false)
        builder.setMessage("A banca venceu")
            .setTitle("Perdeu :(")

        builder.apply {
            setPositiveButton("Novo jogo",
                DialogInterface.OnClickListener { dialog, id ->
                    resetGame()
                    dialog.dismiss()
                })
            setNegativeButton("Sair",
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                    dialog.dismiss()
                })
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun dialogEmpate() {
        empate()
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.setCancelable(false)
        builder.setMessage("O jogo empatou")
            .setTitle("Empate")

        builder.apply {
            setPositiveButton("Novo jogo",
                DialogInterface.OnClickListener { dialog, id ->
                    resetGame()
                    dialog.dismiss()
                })
            setNegativeButton("Sair",
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                    dialog.dismiss()
                })
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun vitoriaJogador() {
        binding.buttonSair.visibility = VISIBLE
        binding.buttonNovoJogo.visibility = VISIBLE
        viewModel.jogador.value!!.vitorias = viewModel.jogador.value!!.vitorias?.plus(1)
        viewModel.jogador.value!!.jogos = viewModel.jogador.value!!.jogos?.plus(1)
        viewModel.saveJogador(viewModel.jogador.value!!)
        mostrarDados()
    }

    private fun vitoriaBanca() {
        binding.buttonSair.visibility = VISIBLE
        binding.buttonNovoJogo.visibility = VISIBLE
        viewModel.jogador.value!!.derrotas = viewModel.jogador.value!!.derrotas?.plus(1)
        viewModel.jogador.value!!.jogos = viewModel.jogador.value!!.jogos?.plus(1)
        viewModel.saveJogador(viewModel.jogador.value!!)
        mostrarDados()
    }

    private fun empate() {
        binding.buttonSair.visibility = VISIBLE
        binding.buttonNovoJogo.visibility = VISIBLE
        viewModel.jogador.value!!.empates = viewModel.jogador.value!!.empates?.plus(1)
        viewModel.jogador.value!!.jogos = viewModel.jogador.value!!.jogos?.plus(1)
        viewModel.saveJogador(viewModel.jogador.value!!)
        mostrarDados()
    }

    private fun resetGame() {
        baralho = Baralho().cartas
        rodada = 0
        pontuacaoRodadaJogador = 0
        pontuacaoRodadaBanca = 0

        binding.buttonSair.visibility = GONE
        binding.buttonNovoJogo.visibility = GONE

        binding.imageCardJogador1.visibility = GONE
        binding.imageCardJogador2.visibility = GONE
        binding.imageCardJogador3.visibility = GONE
        binding.imageCardJogador4.visibility = GONE
        binding.imageCardJogador5.visibility = GONE

        binding.imageCardBanca1.visibility = GONE
        binding.imageCardBanca2.visibility = GONE
        binding.imageCardBanca3.visibility = GONE
        binding.imageCardBanca4.visibility = GONE
        binding.imageCardBanca5.visibility = GONE

        binding.imageBaralho.setImageResource(R.drawable.carta_fundo)
        binding.textSuaPontuacao.setText(R.string.pontuacao_jogador)
        binding.textPontuacaoComputador.setText(R.string.pontuacao_banca)
    }

    private fun mostrarDados() {
        Log.d("xxx", "Dados:")
        Log.d("xxx", "NOME: ${viewModel.jogador.value!!.nome}")
        Log.d("xxx", "blackJack: ${viewModel.jogador.value!!.blackJack}")
        Log.d("xxx", "derrotas: ${viewModel.jogador.value!!.derrotas}")
        Log.d("xxx", "vitorias: ${viewModel.jogador.value!!.vitorias}")
        Log.d("xxx", "empates: ${viewModel.jogador.value!!.empates}")
        Log.d("xxx", "jogos: ${viewModel.jogador.value!!.jogos}")
        Log.d("xxx", " ")
    }
}