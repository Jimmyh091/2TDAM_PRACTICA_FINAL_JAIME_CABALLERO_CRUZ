package com.example.practicafinal.pedidos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityVerPedidosBinding
import com.example.practicafinal.menu.Menu

class VerPedidos : AppCompatActivity() {

    private lateinit var binding: ActivityVerPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityVerPedidosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.verPedidosBannerAtras.setOnClickListener {
            finish()
        }

        binding.verPedidosBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verPedidosBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }


    }
}