package com.example.practicafinal.cartas

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.menu.Menu
import com.example.practicafinal.R
import com.example.practicafinal.Util
import com.example.practicafinal.databinding.ActivityVerCartaBinding
import com.example.practicafinal.pedidos.Pedido
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VerCarta : AppCompatActivity() {

    private lateinit var binding: ActivityVerCartaBinding
    private lateinit var idCarta : String

    private lateinit var sp : SharedPreferences
    private lateinit var db_ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityVerCartaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sp = getSharedPreferences("usuario", MODE_PRIVATE)
        db_ref = FirebaseDatabase.getInstance().getReference()

        binding.verCartaBannerAtras.setOnClickListener {
            finish()
        }

        binding.verCartaBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verCartaBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        idCarta = intent.getStringExtra("idCarta").toString()

        Util.obtenerCarta(db_ref, idCarta) {
            binding.verCartaNombre.text = it?.nombre
            binding.verCartaPrecio.text = it?.precio.toString()
            binding.verCartaCategoria.text = it?.categoria
            binding.verCartaDescripcion.text = it?.descripcion
            binding.verCartaStock.text = it?.stock.toString()
            binding.verCartaImagen.setImageResource(it?.imagen) // ?
        }

        val carta = Carta(
            nombre = binding.verCartaNombre.text.toString(),
            descripcion = binding.verCartaDescripcion.text.toString(),
            categoria = binding.verCartaCategoria.text.toString(),
            precio = binding.verCartaPrecio.text.toString().toFloat(),
            imagen = null,
            stock = binding.verCartaStock.text.toString().toInt()
        )

        binding.verCartaNombre.text = carta.nombre
        binding.verCartaPrecio.text = carta.precio.toString()
        binding.verCartaCategoria.text = carta.categoria
        binding.verCartaDescripcion.text = carta.descripcion
        binding.verCartaStock.text = carta.stock.toString()

        binding.verCartaImagen.setImageResource(R.drawable.archivovacio)

        binding.verCartaEditar.setOnClickListener {
            val intent = Intent(this, ModificarCarta::class.java)
            intent.putExtra("idCarta", idCarta)
            startActivity(intent)
        }

        binding.verCartaBorrar.setOnClickListener {
            Toast.makeText(this, "No se permite borrar", Toast.LENGTH_SHORT).show()
        }

        binding.verCartaBotonCrearPedido.setOnClickListener {

            if (sp.getFloat("dinero", 0f) < carta.precio) {
                Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show()
            }else{

                var sp = getSharedPreferences("usuario", MODE_PRIVATE)

                val pedido = Pedido(usuario = sp.getString("nombre", "")!!, carta = idCarta)

                var key = db_ref.child("tienda").child("pedidos").push().key
                db_ref.child("tienda").child("pedidos").child(key!!).setValue(pedido)

                Toast.makeText(this, "Pedido creado", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)

            }

        }
    }

    fun obtenerCarta(idCarta : String) : Carta {
        /*
        OBTENER CARTAS Y DEVOLVER LA QUE TIENE LA ID
        */

        return Carta()
    }
}