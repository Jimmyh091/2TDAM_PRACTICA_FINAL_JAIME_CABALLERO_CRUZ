package com.example.practicafinal.cartas

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.MenuPrincipal
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityCrearCartaBinding

class CrearCarta : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCartaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCrearCartaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo"))
        binding.crearCartaSpinnerCategoria.adapter = adapter

        binding.crearCartaImagen.setOnClickListener{
            /*
            COGER IMAGEN DEL MOVIL
            */
        }

        binding.crearCartaBotonCrear.setOnClickListener {

            /* BD
            COGER CARTAS
            */

            val listaCartas = listOf(Carta())

            var valido = true

            for (carta in listaCartas) {

                if (carta.nombre == binding.crearCartaTietTitulo.text.toString()) {
                    valido = false
                    break
                }
            }

            if (valido) {

                val carta = Carta(
                    binding.crearCartaTietTitulo.text.toString(),
                    binding.crearCartaTietDescripcion.text.toString(),
                    binding.crearCartaSpinnerCategoria.selectedItem.toString(),
                    binding.crearCartaTietPrecio.text.toString().toFloat(),
                    null,
                    binding.crearCartaTietStock.text.toString().toInt(),
                )

                /* BD
                SUBIR CARTA A LA BASE DE DATOS
                */

            }else{
                Toast.makeText(this, "Ya existe una carta con ese nombre", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}