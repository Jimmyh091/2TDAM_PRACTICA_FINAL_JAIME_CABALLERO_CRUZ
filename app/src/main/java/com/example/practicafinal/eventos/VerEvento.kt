package com.example.practicafinal.eventos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityVerEventoBinding
import com.example.practicafinal.menu.Menu

class VerEvento : AppCompatActivity() {
    
    private lateinit var binding : ActivityVerEventoBinding
    private lateinit var idEvento : String

    override fun onCreate(savedInstanceState: Bundle?) {
        
        binding = ActivityVerEventoBinding.inflate(layoutInflater)
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.verEventoBannerAtras.setOnClickListener {
            finish()
        }

        binding.verEventoBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verEventoBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        idEvento = intent.getStringExtra("idEvento").toString()

        val Evento = obtenerEvento(idEvento)

        binding.verEventoNombre.text = Evento.nombre
        binding.verEventoFecha.text = Evento.fecha
        binding.verEventoDescripcion.text = Evento.descripcion
        binding.verEventoPrecio.text = Evento.precio.toString()
        binding.verEventoAforo.text = Evento.aforo_max.toString()
        // recyclar jaja
        // binding.verEventoUsuarios.text = Evento.usuarios.toString()

        binding.verEventoImagen.setOnClickListener {
            /*
            COGER IMAGEN DEL MOVIL
            */
        }

        binding.verEventoBotonParticipar.setOnClickListener {

            var evento = Evento(
                binding.verEventoNombre.text.toString(),
                binding.verEventoDescripcion.text.toString(),
                binding.verEventoFecha.text.toString(),
                binding.verEventoPrecio.text.toString().toFloat(),
                binding.verEventoAforo.text.toString().toInt(),
            )

            /* BD
            SUBIR EVENTO
            */
        }

        binding.verEventoBorrar.setOnClickListener {
            /*
            BORRAR EVENTO
            */
        }

        binding.verEventoEditar.setOnClickListener {
            val intent = Intent(this, ModificarEvento::class.java)
            intent.putExtra("idEvento", idEvento)
            startActivity(intent)
        }
        
    }

    fun obtenerEvento(idEvento : String) : Evento {
        /*
        OBTENER EVENTOS Y DEVOLVER LA QUE TIENE LA ID
        */

        return Evento()
    }
}