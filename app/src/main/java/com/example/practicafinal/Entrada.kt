package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityEntradaBinding

class Entrada : AppCompatActivity() {

    private lateinit var binding: ActivityEntradaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityEntradaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.entradaLogo.setImageResource(R.drawable.magic)

        binding.entradaBotonInicioSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.entradaBotonRegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

    }
}