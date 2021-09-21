package br.com.blackjack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.blackjack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textJogar.setOnClickListener {
            openGame()
        }

        binding.textSair.setOnClickListener {
            finish()
        }

        binding.textRegras.setOnClickListener {
            openRegras()
        }

        binding.textRaking.setOnClickListener {
            openRaking()
        }
    }

    private fun openGame() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun openRegras() {
        //TODO("Not yet implemented")
    }

    private fun openRaking() {
        //TODO("Not yet implemented")
    }
}