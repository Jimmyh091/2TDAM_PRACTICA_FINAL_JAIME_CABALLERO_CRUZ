package com.example.practicafinal.eventos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.MenuPrincipal
import com.example.practicafinal.R
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.databinding.ActivityCrearEventoBinding

class CrearEvento : AppCompatActivity() {

    private lateinit var binding: ActivityCrearEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCrearEventoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.crearEventoFoto.setOnClickListener {
            /*
            COGER FOTO DEL MOVIL
            */
        }

        binding.crearEventoBotonCrear.setOnClickListener {

            /* BD
            COGER CARTAS
            */

            val listaEventos = listOf(Evento())

            var valido = true

            for (evento in listaEventos) {

                if (evento.nombre == binding.crearEventoTietNombre.text.toString() || evento.fecha == binding.crearEventoTietFecha.text.toString()) {
                    valido = false
                    break
                }
            }

            if (valido) {

                val evento = Evento(
                    binding.crearEventoTietNombre.text.toString(),
                    binding.crearEventoTietDescripcion.text.toString(),
                    binding.crearEventoTietFecha.text.toString(),
                    binding.crearEventoTietPrecio.text.toString().toFloat(),
                    binding.crearEventoTietAforo.text.toString().toInt(),
                )

                /* BD
                SUBIR EVENTO A LA BASE DE DATOS
                */

                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, "Ya existe un evento con ese nombre o en esa fecha", Toast.LENGTH_LONG).show()
            }

            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)

        }
    }
}