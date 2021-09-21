package br.com.blackjack.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["textValue", "textValueEmpty"], requireAll = true)
fun setTextOrEmpty(tv: TextView, textValue: Any?, textValueEmpty: String) {
    if (textValue == null) {
        tv.text = textValueEmpty
        return
    }
    tv.text = textValue.toString()
}