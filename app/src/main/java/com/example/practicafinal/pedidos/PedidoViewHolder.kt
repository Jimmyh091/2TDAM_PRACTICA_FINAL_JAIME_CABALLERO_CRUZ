package com.example.practicafinal.pedidos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R

class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvUsuario: TextView = itemView.findViewById(R.id.itemPedido_usuario)
    private val tvCarta: TextView = itemView.findViewById(R.id.itemPedido_carta)

    fun bind(pedido: Pedido) {
        tvUsuario.text = pedido.usuario
        tvCarta.text = pedido.carta
    }

}
