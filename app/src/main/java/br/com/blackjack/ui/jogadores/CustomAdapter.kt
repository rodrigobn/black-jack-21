package br.com.blackjack.ui.jogadores

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.blackjack.R
import br.com.blackjack.data.model.Jogador
import java.util.ArrayList


class CustomAdapter(private val dataSet: ArrayList<Jogador>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_jogador, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.colocacaoTextView.text = (position + 1).toString() + "º"
        viewHolder.nameTextView.text = dataSet[position].nome
        viewHolder.blackJackTextView.text = dataSet[position].blackJack.toString()
        viewHolder.vitoriasTextView.text = dataSet[position].vitorias.toString()
        viewHolder.empatesTextView.text = dataSet[position].empates.toString()
        viewHolder.derrotasTextView.text = dataSet[position].derrotas.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colocacaoTextView: TextView = view.findViewById(R.id.colocacao)
        val nameTextView: TextView = view.findViewById(R.id.name)
        val blackJackTextView: TextView = view.findViewById(R.id.value_black_jack)
        val vitoriasTextView: TextView = view.findViewById(R.id.value_vitorias)
        val empatesTextView: TextView = view.findViewById(R.id.value_empates)
        val derrotasTextView: TextView = view.findViewById(R.id.value_derrotas)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }
}
