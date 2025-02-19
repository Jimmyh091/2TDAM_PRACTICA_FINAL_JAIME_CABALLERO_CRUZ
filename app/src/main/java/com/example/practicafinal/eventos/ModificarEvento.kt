package com.example.practicafinal.eventos

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.menu.Menu
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityModificarEventoBinding

class ModificarEvento : AppCompatActivity() {
    
    private lateinit var binding: ActivityModificarEventoBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        binding = ActivityModificarEventoBinding.inflate(layoutInflater)
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.modificarEventoBannerAtras.setOnClickListener {
            finish()
        }

        binding.modificarEventoBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.modificarEventoBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        binding.modificarEventoFoto.setOnClickListener {
            /*
            COGER FOTO DEL MOVIL
            */
        }

        binding.modificarEventoTietFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val año = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val día = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                binding.modificarEventoTietFecha.setText(fechaSeleccionada)
            }, año, mes, día)

            datePicker.show()
        }

        binding.modificarEventoBotonGuardar.setOnClickListener {

            /* BD
            COGER CARTAS
            */

            val listaEventos = listOf(Evento())

            if (
                binding.modificarEventoTietNombre.text.toString().isEmpty() ||
                binding.modificarEventoTietDescripcion.text.toString().isEmpty() ||
                binding.modificarEventoTietFecha.text.toString().isEmpty() ||
                binding.modificarEventoTietPrecio.text.toString().isEmpty() ||
                binding.modificarEventoTietAforo.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show()
            }else{

                var valido = true

                for (evento in listaEventos) {

                    if (evento.nombre == binding.modificarEventoTietNombre.text.toString() || evento.fecha == binding.modificarEventoTietFecha.text.toString()) {
                        valido = false
                        break
                    }
                }

                if (valido) {

                    val evento = Evento(
                        binding.modificarEventoTietNombre.text.toString(),
                        binding.modificarEventoTietDescripcion.text.toString(),
                        binding.modificarEventoTietFecha.text.toString(),
                        binding.modificarEventoTietPrecio.text.toString().toFloat(),
                        binding.modificarEventoTietAforo.text.toString().toInt(),
                    )

                    /* BD
                    SUBIR EVENTO A LA BASE DE DATOS
                    */

                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Ya existe un evento con ese nombre o en esa fecha", Toast.LENGTH_LONG).show()
                }

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)

            }

        }
        
    }
}