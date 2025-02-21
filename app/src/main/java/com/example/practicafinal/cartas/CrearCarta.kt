package com.example.practicafinal.cartas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.menu.Menu
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityCrearCartaBinding

class CrearCarta : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCartaBinding

    //private lateinit var storage: StorageReference
    private var rutaImagen: Uri? = null
    private lateinit var imagen : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCrearCartaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.crearCartaBannerAtras.setOnClickListener {
            finish()
        }

        binding.crearCartaBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.crearCartaBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf(resources.getStringArray(R.array.categorias)))
        binding.crearCartaSpinnerCategoria.adapter = adapter

        binding.crearCartaImagen.setOnClickListener{
            accesoGaleria.launch("image/*")
        }

        binding.crearCartaBotonCrear.setOnClickListener {

            /* BD
            COGER CARTAS
            */

            val listaCartas = listOf(Carta())

            var valido = true

            if (
                binding.crearCartaTietTitulo.text.toString().isEmpty() ||
                binding.crearCartaTietDescripcion.text.toString().isEmpty() ||
                binding.crearCartaTietPrecio.text.toString().isEmpty() ||
                binding.crearCartaTietStock.text.toString().isEmpty()
            ){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }else{

                for (carta in listaCartas) {

                    if (carta.nombre == binding.crearCartaTietTitulo.text.toString()) {
                        valido = false
                        break
                    }
                }

                if (valido) {

                    val carta = Carta(
                        binding.crearCartaTietTitulo.text.toString(),
                        binding.crearCartaTietDescripcion.text.toString(),
                        binding.crearCartaSpinnerCategoria.selectedItem.toString(),
                        binding.crearCartaTietPrecio.text.toString().toFloat(),
                        null,
                        binding.crearCartaTietStock.text.toString().toInt(),
                    )

                    /* BD
                    SUBIR CARTA A LA BASE DE DATOS
                    */

                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Ya existe una carta con ese nombre", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val accesoGaleria = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        if (uri != null) {
            rutaImagen = uri
            imagen.setImageURI(rutaImagen)
        }
    }
}