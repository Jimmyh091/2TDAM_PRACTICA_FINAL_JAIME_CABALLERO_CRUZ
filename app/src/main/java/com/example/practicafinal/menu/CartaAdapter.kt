package com.example.practicafinal.menu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.cartas.VerCarta

class CartaAdapter(private val cartas: List<Carta>, private val listener: onCartaClickedListener, val contexto: Context) : RecyclerView.Adapter<CartaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carta, parent, false)
        return CartaViewHolder(view)
    }

    override fun getItemCount(): Int = cartas.size

    override fun onBindViewHolder(holder: CartaViewHolder, position: Int) {
        holder.bind(cartas[position], contexto)

        holder.itemView.setOnClickListener {

            var intent = Intent(holder.itemView.context, VerCarta::class.java)
            intent.putExtra("carta", cartas[position].nombre)
            holder.itemView.context.startActivity(intent)

            listener.onCartaClicked(cartas[position])
        }
    }

}