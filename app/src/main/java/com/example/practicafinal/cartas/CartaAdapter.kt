package com.example.practicafinal.cartas

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R

class CartaAdapter(private val cartas: List<Carta>, private val listener: onCartaClickedListener) : RecyclerView.Adapter<CartaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carta, parent, false)
        return CartaViewHolder(view)
    }

    override fun getItemCount(): Int = cartas.size

    override fun onBindViewHolder(holder: CartaViewHolder, position: Int) {
        holder.bind(cartas[position])

        holder.itemView.setOnClickListener {

            var intent = Intent(holder.itemView.context, VerCarta::class.java)
            intent.putExtra("carta", cartas[position])
            holder.itemView.context.startActivity(intent)

            listener.onCartaClicked(cartas[position])
        }
    }

}