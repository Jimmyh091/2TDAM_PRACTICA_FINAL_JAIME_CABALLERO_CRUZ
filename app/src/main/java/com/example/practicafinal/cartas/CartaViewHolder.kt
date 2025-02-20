package com.example.practicafinal.cartas

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R

class CartaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvNombre: TextView = itemView.findViewById(R.id.itemCarta_nombre)
    private val tvImage: TextView = itemView.findViewById(R.id.itemCarta_foto)

    fun bind(carta: Carta) {
        tvNombre.text = carta.nombre
        tvImage.setText(carta.imagen)
    }


}