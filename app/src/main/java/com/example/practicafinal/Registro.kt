package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegistroBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.registroBotonRegistro.setOnClickListener {

            val usuario = binding.registroTietUsuario.text.toString()
            val correo = binding.registroTietCorreo.text.toString()
            val contrasenia = binding.registroTietContrasenia.text.toString()
            val contrasenia2 = binding.registroTietRepetirContrasenia.text.toString()

            /* BD
            PEDIR USUARIOS BASE DE DATOS
            */
            var listaUser = listOf(Usuario())

            var valido = true

            for (user in listaUser){
                if (usuario == user.nombre) {

                    valido = false
                    break
                }
            }

            if (valido){

                var usuario = Usuario(
                    admin = false,
                    nombre = usuario,
                    correo = correo,
                    contrasenia = contrasenia,
                    dinero = 0f
                )

                /* BD
                SUBIR USUARIO A BASE DE DATOS
                */

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                actualizarShared(usuario)

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun actualizarShared(usuario: Usuario){
        var usuarioSharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()

        editor.clear()
        editor.putString("nombre", usuario.nombre)
        editor.putString("contrasenia", usuario.contrasenia)
        editor.putString("correo", usuario.correo)
        editor.putBoolean("admin", usuario.admin)
        editor.putFloat("dinero", usuario.dinero)
        editor.putString("foto", usuario.imagen)

        editor.apply()
    }
}