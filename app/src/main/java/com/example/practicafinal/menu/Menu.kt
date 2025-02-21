package com.example.practicafinal.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.cartas.CrearCarta
import com.example.practicafinal.cartas.VerCarta
import com.example.practicafinal.databinding.ActivityMenuBinding
import com.example.practicafinal.eventos.CrearEvento
import com.example.practicafinal.eventos.Evento
import com.example.practicafinal.eventos.VerEvento
import com.example.practicafinal.pedidos.VerPedidos
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Menu : AppCompatActivity(), onCartaClickedListener, onEventoClickedListener {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMenuBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.menuBannerAtras.setOnClickListener {
            finish()
        }

        binding.menuBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.menuBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val sp = getSharedPreferences("usuario", MODE_PRIVATE)

        if (!sp.getBoolean("admin", true)){
            binding.menuBotonAniadirCarta.isEnabled = false
            binding.menuBotonAniadirCarta.isVisible = false

            binding.menuBotonAniadirEvento.isEnabled = false
            binding.menuBotonAniadirEvento.isVisible = false

            binding.menuBotonGestionarPedidos.isEnabled = false
            binding.menuBotonGestionarPedidos.isVisible = false
        }

        binding.menuBotonGestionarPedidos.setOnClickListener {
            val intent = Intent(this, VerPedidos::class.java)
            startActivity(intent)
        }

        binding.menuBotonAniadirCarta.setOnClickListener {
            val intent = Intent(this, CrearCarta::class.java)
            startActivity(intent)
        }

        binding.menuBotonAniadirEvento.setOnClickListener {
            val intent = Intent(this, CrearEvento::class.java)
            startActivity(intent)
        }

        binding.menuTietBuscarCartas.addTextChangedListener {
            val searchText = binding.menuTietBuscarCartas.text.toString().trim()
            // filtra el recycler por nombre
        }

        var lisa = getResources().getStringArray(R.array.categorias)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lisa )
        binding.menuSpinnerCategoriaCartas.adapter = adapter
        binding.menuSpinnerCategoriaCartas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // filtrar el recycler por categoria
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // otnto
            }
        }

        var listaCartas = mutableListOf<Carta>()
        val cartas = obtenerCartas { cartas ->
            listaCartas.addAll(cartas)
        }

        var listaEventos = mutableListOf<Evento>()
        val eventos = obtenerEventos { eventos ->
            listaEventos.addAll(eventos)
        }

        binding.menuRecyclerCartas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.menuRecyclerCartas.adapter = CartaAdapter(listaCartas, this)

        binding.menuRecyclerEventos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.menuRecyclerEventos.adapter = EventoAdapter(listaEventos, this)
    }

    override fun onCartaClicked(carta: Carta) {
        var intent = Intent(this, VerCarta::class.java)
        intent.putExtra("carta", carta.nombre)
        startActivity(intent)
    }

    override fun onEventoClicked(evento: Evento) {
        var intent = Intent(this, VerEvento::class.java)
        intent.putExtra("carta", evento.nombre)
        startActivity(intent)
    }

    fun obtenerCartas(onResult: (List<Carta>) -> Unit) {
        val database = FirebaseDatabase.getInstance().getReference("tienda").child("cartas")
        val listaCartas = mutableListOf<Carta>()

        database.get().addOnSuccessListener { snapshot ->
            for (cartaSnapshot in snapshot.children) {
                val carta = cartaSnapshot.getValue(Carta::class.java)
                carta?.let { listaCartas.add(it) }
            }
            onResult(listaCartas)
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
            onResult(emptyList())
        }
    }

    fun obtenerEventos(onResult: (List<Evento>) -> Unit) {
        val database = FirebaseDatabase.getInstance().getReference("tienda").child("eventos")
        val listaEventos = mutableListOf<Evento>()

        database.get().addOnSuccessListener { snapshot ->
            for (eventoSnapshot in snapshot.children) {
                val evento = eventoSnapshot.getValue(Evento::class.java)
                evento?.let { listaEventos.add(it) }
            }
            onResult(listaEventos) // Devolvemos la lista cuando se completa
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
            onResult(emptyList()) // En caso de error, devolvemos una lista vacía
        }
    }

}