package br.com.blackjack.ui.jogadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.blackjack.R
import br.com.blackjack.data.model.Jogador
import br.com.blackjack.di.TinyDB
import br.com.blackjack.ui.GameViewModel.Companion.KEY_JOGADORES
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaJogadoresActivity : AppCompatActivity() {

    private val viewModel: ListaJogadoresViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_jogadores)

        val tinyDB = TinyDB(this)
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        // ArrayList of class ItemsViewModel
        val data = tinyDB.getListObject(KEY_JOGADORES, Jogador::class.java) as ArrayList<Jogador>
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(sortBlackJack(data))
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    private fun sortBlackJack(jogadores: java.util.ArrayList<Jogador>): ArrayList<Jogador> {
        val n = jogadores.size
        var temp:Jogador
        for (i in 0 until n){
            var indexOfMin = i
            for(j in n-1 downTo  i){
                if(jogadores[j].blackJack!! > jogadores[indexOfMin].blackJack!!)
                    indexOfMin=j
            }
            if(i!=indexOfMin){
                temp = jogadores[i]
                jogadores[i]= jogadores[indexOfMin]
                jogadores[indexOfMin] = temp
            }
        }
        return jogadores
    }
}