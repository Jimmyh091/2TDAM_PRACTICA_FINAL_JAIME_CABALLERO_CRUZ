package com.example.practicafinal.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.pedidos.Pedido
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var sp : SharedPreferences
    private lateinit var db_ref : DatabaseReference

    private val tvNombre: TextView = itemView.findViewById(R.id.itemCarta_nombre)
    private val tvImage: ImageView = itemView.findViewById(R.id.itemCarta_foto)

    private val comprar: Button = itemView.findViewById(R.id.itemCarta_boton_comprar)

    fun bind(carta: Carta, context: Context) {

        sp = context.getSharedPreferences("usuario", MODE_PRIVATE)
        db_ref = FirebaseDatabase.getInstance().getReference()

        tvNombre.text = carta.nombre
        tvImage.setImageResource(R.drawable.archivovacio)

        comprar.setOnClickListener {
            if (sp.getFloat("dinero", 0f) < carta.precio) {
                Toast.makeText(context, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show()
            }else{

                var sp = context.getSharedPreferences("usuario", MODE_PRIVATE)

                val pedido = Pedido(usuario = sp.getString("nombre", "")!!, carta = carta.nombre)

                var key = db_ref.child("tienda").child("pedidos").push().key
                db_ref.child("tienda").child("pedidos").child(key!!).setValue(pedido)

                Toast.makeText(context, "Pedido creado", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, Menu::class.java)
                context.startActivity(intent)

            }
        }

    }
}