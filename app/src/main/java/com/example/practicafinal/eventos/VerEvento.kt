package com.example.practicafinal.eventos

import android.R.id
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.Perfil
import com.example.practicafinal.R
import com.example.practicafinal.Util
import com.example.practicafinal.databinding.ActivityVerEventoBinding
import com.example.practicafinal.inscripcion.Inscripcion
import com.example.practicafinal.menu.Menu
import com.example.practicafinal.pedidos.Pedido
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VerEvento : AppCompatActivity() {

    private lateinit var db_ref: DatabaseReference
    private lateinit var sp: SharedPreferences

    private lateinit var id_firebase : String

    private lateinit var binding : ActivityVerEventoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        
        binding = ActivityVerEventoBinding.inflate(layoutInflater)
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sp = getSharedPreferences("usuario", MODE_PRIVATE)
        db_ref = FirebaseDatabase.getInstance().getReference("tienda").child("eventos")

        if (intent.extras != null) {

            var evento: Evento? = null
            Util.obtenerEvento(db_ref, intent.extras!!.getString("evento")!!) {

                if (it != null) {
                    evento = it
                }

                id_firebase = evento?.id_firebase.toString()
                binding.verEventoNombre.setText(evento?.nombre ?: "ERROR")
                binding.verEventoDescripcion.setText(evento?.descripcion ?: "ERROR")
                binding.verEventoPrecio.setText(evento?.precio.toString())
                binding.verEventoAforo.setText(evento?.aforo_max.toString())

            }
        }

        binding.verEventoBannerAtras.setOnClickListener {
            finish()
        }

        binding.verEventoBannerLogo.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        binding.verEventoBannerFotoPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        id_firebase = intent.getStringExtra("idEvento").toString()

        val Evento = obtenerEvento(id_firebase)

        binding.verEventoNombre.text = Evento.nombre
        binding.verEventoFecha.text = Evento.fecha
        binding.verEventoDescripcion.text = Evento.descripcion
        binding.verEventoPrecio.text = Evento.precio.toString()
        binding.verEventoAforo.text = Evento.aforo_max.toString()
        // recyclar jaja
        // binding.verEventoUsuarios.text = Evento.usuarios.toString()

        binding.verEventoImagen.setOnClickListener {
            /*
            COGER IMAGEN DEL MOVIL
            */
        }

        binding.verEventoBotonParticipar.setOnClickListener {

            var evento = Evento(
                id_firebase,
                binding.verEventoNombre.text.toString(),
                binding.verEventoDescripcion.text.toString(),
                binding.verEventoFecha.text.toString(),
                binding.verEventoPrecio.text.toString().toFloat(),
                binding.verEventoAforo.text.toString().toInt(),
            )

            if (sp.getFloat("dinero", 0f) < evento.precio) {
                Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show()
            }else{

                var sp = getSharedPreferences("usuario", MODE_PRIVATE)

                var key = db_ref.child("tienda").child("inscripcion").push().key

                val pedido = Inscripcion(key, sp.getString("nombre", "")!!, id_firebase)
                db_ref.child("tienda").child("pedidos").child(key!!).setValue(pedido)

                Toast.makeText(this, "Inscripcion creada", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)

            }
        }

        binding.verEventoBorrar.setOnClickListener {
            Toast.makeText(this, "Opcion no requerida", Toast.LENGTH_SHORT).show()
        }

        binding.verEventoEditar.setOnClickListener {
            val intent = Intent(this, ModificarEvento::class.java)
            intent.putExtra("idEvento", id_firebase)
            startActivity(intent)
        }
        
    }

    fun obtenerEvento(idEvento : String) : Evento {
        /*
        OBTENER EVENTOS Y DEVOLVER LA QUE TIENE LA ID
        */

        return Evento()
    }
}