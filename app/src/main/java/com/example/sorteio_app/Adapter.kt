package com.example.sorteio_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
        private val lista :MutableList<Model>
) : RecyclerView.Adapter<AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = lista[position]
        holder.nome.text = item.nome
        holder.removerButton.setOnClickListener{
            deleteItem(position)
        }

    }

    fun deleteItem(position: Int) {
        lista.removeAt(position)
        notifyDataSetChanged()
        var aN = ActivityNomes()
        aN.setButonSortear()
    }
    fun deleteElement(element : Model){
        lista.remove(element)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}

class AdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val nome = itemView.findViewById<TextView>(R.id.nome_item)
    val removerButton = itemView.findViewById<Button>(R.id.Remover_Button)
}