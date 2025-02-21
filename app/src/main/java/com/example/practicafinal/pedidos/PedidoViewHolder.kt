package com.example.practicafinal.pedidos

import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var db_ref : DatabaseReference

    private val tvUsuario: TextView = itemView.findViewById(R.id.itemPedido_usuario)
    private val tvCarta: TextView = itemView.findViewById(R.id.itemPedido_carta)

    private val switch : Switch = itemView.findViewById(R.id.itemPedido_switch)
    private val enviar : ImageView = itemView.findViewById(R.id.itemPedido_boton_enviar)

    fun bind(pedido: Pedido) {

        db_ref = FirebaseDatabase.getInstance().getReference()

        tvUsuario.text = pedido.usuario
        tvCarta.text = pedido.carta

        enviar.setOnClickListener {

            val k = db_ref.child("tienda").child("pedidos").push().key
            if (k != null) {
                db_ref.child("tienda").child("pedidos").child(k).setValue(pedido)
            }
        }
    }

}
