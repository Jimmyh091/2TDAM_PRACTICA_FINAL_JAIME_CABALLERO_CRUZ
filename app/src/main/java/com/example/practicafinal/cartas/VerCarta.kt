package com.example.practicafinal.cartas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityVerCartaBinding

class VerCarta : AppCompatActivity() {

    private lateinit var binding: ActivityVerCartaBinding
    private lateinit var idCarta : String

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityVerCartaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idCarta = intent.getStringExtra("idCarta").toString()

        val carta = obtenerCarta(idCarta)

        binding.verCartaNombre.text = carta.nombre
        binding.verCartaPrecio.text = carta.precio.toString()
        binding.verCartaCategoria.text = carta.categoria
        binding.verCartaDescripcion.text = carta.descripcion
        binding.verCartaStock.text = carta.stock.toString()

        binding.verCartaImagen.setOnClickListener {
            /*
            COGER IMAGEN DEL MOVIL
            */
        }

    }

    fun obtenerCarta(idCarta : String) : Carta {
        /*
        OBTENER CARTAS Y DEVOLVER LA QUE TIENE LA ID
        */

        return Carta()
    }
}