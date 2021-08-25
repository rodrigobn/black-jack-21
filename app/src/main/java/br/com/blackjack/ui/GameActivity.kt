package br.com.blackjack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.blackjack.R

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}