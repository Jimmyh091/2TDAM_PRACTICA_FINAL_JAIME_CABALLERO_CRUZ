package com.example.practicafinal.menu

import android.content.Intent
import android.os.Bundle
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
import com.google.firebase.database.DatabaseReference

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

        val cartas = listOf(
            Carta(),
            Carta(),
            Carta(),
            Carta(),
            Carta(),
            Carta()
        )

        val eventos = listOf(
            Evento(),
            Evento(),
            Evento(),
            Evento(),
            Evento()
        )

        binding.menuRecyclerCartas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.menuRecyclerCartas.adapter = CartaAdapter(cartas, this)

        binding.menuRecyclerEventos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.menuRecyclerEventos.adapter = EventoAdapter(eventos, this)
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

    fun obtenerCartas(db_ref: DatabaseReference, callback: (List<Carta>?) -> Unit) {
        db_ref.child("tienda").child("cartas").get()
            .addOnSuccessListener { snapshot ->
                // Comprobar si existen cartas en la base de datos
                if (snapshot.exists()) {
                    // Mapear las cartas y convertirlas en objetos Carta
                    val cartas = snapshot.children.mapNotNull { it.getValue(Carta::class.java) }
                    callback(cartas)  // Llamar al callback con la lista de cartas
                } else {
                    callback(null)  // No se encontraron cartas
                }
            }
            .addOnFailureListener { exception ->
                // En caso de error
                callback(null)
            }
    }

    fun obtenerEventos(db_ref: DatabaseReference, callback: (List<Evento>?) -> Unit) {
        db_ref.child("tienda").child("eventos").get()
            .addOnSuccessListener { snapshot ->
                // Verifica si existen eventos en la base de datos
                if (snapshot.exists()) {
                    // Mapea los eventos y los convierte en objetos Evento
                    val eventos = snapshot.children.mapNotNull { it.getValue(Evento::class.java) }
                    callback(eventos)  // Llama al callback con la lista de eventos
                } else {
                    callback(null)  // No se encontraron eventos
                }
            }
            .addOnFailureListener { exception ->
                // En caso de error
                callback(null)
            }
    }


}