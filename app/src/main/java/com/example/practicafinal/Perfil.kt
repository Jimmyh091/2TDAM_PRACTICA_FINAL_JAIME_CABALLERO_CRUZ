package com.example.practicafinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityPerfilBinding

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

        val sp = getSharedPreferences("usuario", MODE_PRIVATE)

        binding.perfilNombre.text = sp.getString("nombre", "")
        binding.perfilCorreo.text = sp.getString("correo", "")
        binding.perfilDinero.text = sp.getString("dinero", "")

        binding.perfilBotonAniadirDinero.setOnClickListener {

        }

        binding.perfilRecyclerCartas
        binding.perfilRecyclerEventos

    }
}