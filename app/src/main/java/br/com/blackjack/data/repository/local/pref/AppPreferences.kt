package br.com.blackjack.data.repository.local.pref

import android.content.Context
import android.content.SharedPreferences
import br.com.blackjack.data.model.Jogador
import br.com.blackjack.di.TinyDB

object AppPreferences {
    private const val NAME = "PreferencesApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // lista de preferências específicas do aplicativo
    private val PREF_SCORE = Pair("pref_score", 0.0)
    private val PREF_CLEAR = Pair("pref_clear", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * Função de extensão SharedPreferences, então não precisamos chamar edit() e apply()
     * nós mesmos em cada operação de SharedPreferences.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var score: Float
        // getter personalizado para obter uma preferência de um tipo desejado, com um valor padrão predefinido
        get() = preferences.getFloat(PREF_SCORE.first, PREF_SCORE.second.toFloat())
        // configurador personalizado para salvar uma preferência no arquivo de preferências
        set(value) = preferences.edit {
            it.putFloat(PREF_SCORE.first, value)
        }

    var clear: Boolean
        get() = preferences.getBoolean(PREF_CLEAR.first, PREF_CLEAR.second)
        set(value) = preferences.edit {
            it.putBoolean(PREF_CLEAR.first, true)
        }
}