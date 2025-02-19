package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityAjustesBinding

class Ajustes : AppCompatActivity() {

    private lateinit var binding: ActivityAjustesBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAjustesBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ajustesBotonGuardar.setOnClickListener {
            modoOscuro(binding.ajustesSwitchModoOscuro.isChecked)
            divisa(binding.ajustesSpinnerDivisa.selectedItem.toString())
        }

        binding.ajustesBotonCerrarSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    fun modoOscuro(modo : Boolean) {
        var usuarioSharedPreferences = getSharedPreferences("ajustes", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()
        editor.putBoolean("modoOscuro", true)
        editor.apply()
    }

    fun divisa(divisa : String){
        var usuarioSharedPreferences = getSharedPreferences("ajustes", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()
        editor.putString("divisa", divisa)
        editor.apply()
    }
}