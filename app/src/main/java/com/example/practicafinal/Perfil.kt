package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.menu.CartaAdapter
import com.example.practicafinal.menu.onCartaClickedListener
import com.example.practicafinal.databinding.ActivityPerfilBinding
import com.example.practicafinal.menu.Menu
import com.google.firebase.database.FirebaseDatabase

class Perfil : AppCompatActivity(), onCartaClickedListener {

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
        binding.perfilDinero.text = sp.getFloat("dinero", 0f).toString()

        binding.perfilBotonAniadirDinero.setOnClickListener {

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

            modificarUsuarioCompleto(sp.getString("nombre", "")!!, usuarioActual)
            Toast.makeText(this, "Dinero añadido", Toast.LENGTH_SHORT).show()
        }

        var listaCartas = listOf(Carta())

        binding.perfilRecyclerCartas.layoutManager = LinearLayoutManager(this)
        binding.perfilRecyclerCartas.adapter = CartaAdapter(listaCartas, this, this)

        binding.perfilRecyclerEventos.layoutManager = LinearLayoutManager(this)
        binding.perfilRecyclerEventos.adapter = CartaAdapter(listaCartas, this, this)

    }

    fun modificarUsuarioCompleto(userId: String, nuevoUsuario: Usuario) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("tienda").child("usuarios").child(userId)

        userRef.setValue(nuevoUsuario)
            .addOnSuccessListener {
                println("Datos del usuario reemplazados correctamente")
            }
            .addOnFailureListener { error ->
                println("Error al modificar usuario: ${error.message}")
            }
    }

    override fun onCartaClicked(carta: Carta) {
        TODO("Not yet implemented")
    }
}