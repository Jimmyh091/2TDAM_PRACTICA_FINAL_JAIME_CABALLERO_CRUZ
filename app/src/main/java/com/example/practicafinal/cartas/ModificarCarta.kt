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
import com.example.practicafinal.databinding.ActivityModificarCartaBinding

class ModificarCarta : AppCompatActivity() {
    
    private lateinit var binding: ActivityModificarCartaBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        binding = ActivityModificarCartaBinding.inflate(layoutInflater)
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo"))
        binding.modificarCartaSpinnerCategoria.adapter = adapter

        binding.modificarCartaImagen.setOnClickListener{
            /*
            COGER IMAGEN DEL MOVIL
            */
        }

        binding.modificarCartaBotonGuardar.setOnClickListener {

            /* BD
            COGER CARTAS
            */

            val listaCartas = listOf(Carta())

            var valido = false

            for (carta in listaCartas) {

                if (carta.nombre == binding.modificarCartaTietTitulo.text.toString()) {
                    valido = true
                    break
                }
            }

            if (valido) {

                val carta = Carta(
                    binding.modificarCartaTietTitulo.text.toString(),
                    binding.modificarCartaTietDescripcion.text.toString(),
                    binding.modificarCartaSpinnerCategoria.selectedItem.toString(),
                    binding.modificarCartaTietPrecio.text.toString().toFloat(),
                    null,
                    binding.modificarCartaTietStock.text.toString().toInt(),
                )

                /* BD
                ACTUALIZAR CARTA A LA BASE DE DATOS
                */

                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, "La carta no existe", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}