package com.example.practicafinal.pedidos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityVerPedidosBinding
import com.example.practicafinal.menu.Menu
import com.google.firebase.database.FirebaseDatabase

class VerPedidos : AppCompatActivity() {

    private lateinit var binding: ActivityVerPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityVerPedidosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.verPedidosBannerAtras.setOnClickListener {
            finish()
        }

        binding.verPedidosBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verPedidosBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        var listaPedidos = mutableListOf<Pedido>()
        obtenerTodosLosPedidos { pedidos ->
            listaPedidos.addAll(pedidos)
        }

        Log.d("pedidos", listaPedidos.toString())

        binding.verPedidosRecycler.layoutManager = LinearLayoutManager(this)
        binding.verPedidosRecycler.adapter = PedidoAdapter(listaPedidos)

    }

    fun obtenerTodosLosPedidos(onResult: (List<Pedido>) -> Unit) {
        val database = FirebaseDatabase.getInstance().getReference("tienda").child("pedidos")
        val listaPedidos = mutableListOf<Pedido>()

        database.get().addOnSuccessListener { snapshot ->
            for (pedidoSnapshot in snapshot.children) {
                Log.d("pedido", pedidoSnapshot.toString())
                val pedido = pedidoSnapshot.getValue(Pedido::class.java)
                pedido?.let { listaPedidos.add(it) }
            }
            onResult(listaPedidos) // Devolvemos la lista cuando se completa
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
            onResult(emptyList()) // En caso de error, devolvemos una lista vacía
        }
    }


}