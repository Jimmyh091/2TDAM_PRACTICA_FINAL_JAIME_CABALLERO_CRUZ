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
import androidx.core.widget.addTextChangedListener
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.cartas.CrearCarta
import com.example.practicafinal.databinding.ActivityMenuBinding
import com.example.practicafinal.eventos.CrearEvento

class Menu : AppCompatActivity() {

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
            binding.menuBotonAniadirEvento.isEnabled = false
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

        binding.menuRecyclerCartas.adapter = CartaAdapter(cartas)

    }
}