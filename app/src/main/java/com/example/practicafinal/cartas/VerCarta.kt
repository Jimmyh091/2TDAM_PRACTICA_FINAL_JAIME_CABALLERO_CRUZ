package com.example.practicafinal.cartas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.menu.Menu
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityVerCartaBinding
import com.example.practicafinal.pedidos.Pedido

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

        binding.verCartaBannerAtras.setOnClickListener {
            finish()
        }

        binding.verCartaBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verCartaBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
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

        binding.verCartaEditar.setOnClickListener {
            val intent = Intent(this, ModificarCarta::class.java)
            intent.putExtra("idCarta", idCarta)
            startActivity(intent)
        }

        binding.verCartaBorrar.setOnClickListener {
            /* BD
            BORRAR DE BASE DE DATOS
            */
        }

        binding.verCartaBotonCrearPedido.setOnClickListener {

            var sp = getSharedPreferences("usuario", MODE_PRIVATE)

            val pedido = Pedido(sp.getString("nombre", "")!!, idCarta)

            /* BD
            SUBIR PEDIDO
            */

            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

    fun obtenerCarta(idCarta : String) : Carta {
        /*
        OBTENER CARTAS Y DEVOLVER LA QUE TIENE LA ID
        */

        return Carta()
    }
}