package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityAjustesBinding
import com.example.practicafinal.menu.Menu

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

        binding.ajustesBannerAtras.setOnClickListener {
            finish()
        }

        binding.ajustesBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.ajustesBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
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

    fun modoOscuro(modoOscuro : Boolean) {
        var usuarioSharedPreferences = getSharedPreferences("ajustes", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()
        editor.putBoolean("modoOscuro", true)
        editor.apply()

        /* TD
        CAMBIAR EL TEMA A MODO OSCURO
        */
    }

    fun divisa(divisa : String){
        var usuarioSharedPreferences = getSharedPreferences("ajustes", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()
        editor.putString("divisa", divisa)
        editor.apply()
    }
}