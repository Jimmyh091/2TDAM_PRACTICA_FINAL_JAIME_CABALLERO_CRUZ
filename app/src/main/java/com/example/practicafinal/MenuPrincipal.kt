package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.practicafinal.cartas.CrearCarta
import com.example.practicafinal.databinding.ActivityMenuBinding
import com.example.practicafinal.eventos.CrearEvento

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMenuBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sp = getSharedPreferences("usuario", MODE_PRIVATE)

        if (!sp.getBoolean("admin", true)){
            binding.menuBotonAniadirCarta.isEnabled = false
            binding.menuBotonAniadirEvento.isEnabled = false
        }

        binding.menuBotonAniadirCarta.setOnClickListener {
            val intent = Intent(this, CrearCarta::class.java)
            startActivity(intent)
        }

        binding.menuBotonAniadirEvento.setOnClickListener {
            val intent = Intent(this, CrearEvento::class.java)
            startActivity(intent)
        }

        binding.menuTietBuscarCartas.addTextChangedListener {
            val searchText = binding.menuTietBuscarCartas.text.toString().trim()
            // filtra el recycler por nombre
        }


        var lisa = listOf(1,2)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lisa )
        binding.menuSpinnerCategoriaCartas.adapter = adapter
        binding.menuSpinnerCategoriaCartas.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(applicationContext, "textito" + " " +
                        "" + lisa[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

}

    }
}