package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        binding.loginBotonLogin.setOnClickListener {

            val usuario = binding.loginTietUsuario.text.toString()
            val contrasenia = binding.loginTietContrasenia.text.toString()

            /* BD
            PEDIR USUARIOS BASE DE DATOS
            */
            var listaUser = listOf(Usuario())

            var accedido = false

            for (user in listaUser){
                if (usuario == user.nombre && contrasenia == user.contrasenia) {

                    accedido = true

                    actualizarShared(user)

                    val intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)

                    break
                }

            }

            if (!accedido){
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun actualizarShared(usuario: Usuario){
        var usuarioSharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        var editor = usuarioSharedPreferences.edit()

        editor.putString("nombre", usuario.nombre)
        editor.putString("contrasenia", usuario.contrasenia)
        editor.putString("correo", usuario.correo)
        editor.putBoolean("admin", usuario.admin)
        editor.putFloat("dinero", usuario.dinero)
        editor.putString("foto", usuario.imagen)

        editor.apply()
    }
}