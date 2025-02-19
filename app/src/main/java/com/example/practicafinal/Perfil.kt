package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityPerfilBinding
import com.example.practicafinal.menu.Menu

class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPerfilBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.perfilBannerAtras.setOnClickListener {
            finish()
        }

        binding.perfilBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.perfilBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val sp = getSharedPreferences("usuario", MODE_PRIVATE)

        binding.perfilNombre.text = sp.getString("nombre", "")
        binding.perfilCorreo.text = sp.getString("correo", "")
        binding.perfilDinero.text = sp.getString("dinero", "")

        binding.perfilBotonAniadirDinero.setOnClickListener {

            /* BD
            OBTENER DATOS USUARIO COGER CARTAS Y EVENTOS
            */

            var listaCartas = mutableListOf("")
            var listaEventos = mutableListOf("")

            var usuarioActual = Usuario(
                admin = sp.getBoolean("admin", false),
                nombre = sp.getString("nombre", "")!!,
                correo = sp.getString("correo", "")!!,
                contrasenia = sp.getString("contrasenia", "")!!,
                imagen = null,
                dinero = sp.getFloat("dinero", 0f) + 10f,
                cartas_compradas = listaCartas,
                eventos_apuntados = listaEventos
            )

            val editor = sp.edit()
            editor.putFloat("dinero", usuarioActual.dinero)
            editor.apply()

            binding.perfilDinero.text = usuarioActual.dinero.toString()

            /* BD
            ACTUALIZAR DINERO
            */
        }

        binding.perfilRecyclerCartas
        binding.perfilRecyclerEventos

    }
}