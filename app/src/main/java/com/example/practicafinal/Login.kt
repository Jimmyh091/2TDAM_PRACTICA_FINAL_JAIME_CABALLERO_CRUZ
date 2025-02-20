package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityLoginBinding
import com.example.practicafinal.menu.Menu
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db_ref: DatabaseReference

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

        db_ref = FirebaseDatabase.getInstance().reference

        binding.loginBotonLogin.setOnClickListener {
            val nombre = binding.loginTietUsuario.text.toString()
            val contrasenia = binding.loginTietContrasenia.text.toString()

            if (nombre.isEmpty() || contrasenia.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                Log.d("Login", "Campos vacíos")
            } else {
                Log.d("Login", "Intentando iniciar sesión")
                iniciarSesion(db_ref, nombre, contrasenia) { exitoso ->
                    if (exitoso) {
                        Log.d("Login", "Inicio de sesión exitoso")
                        Util.obtenerUsuario(db_ref, nombre) { usuario ->
                            if (usuario != null) {
                                actualizarShared(usuario)
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, Menu::class.java))
                            } else {
                                Toast.makeText(this, "Error al recuperar usuario", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Log.d("Login", "Inicio de sesión fallido")
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
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

    fun iniciarSesion(db_ref: DatabaseReference, nombre: String, contrasenia: String, callback: (Boolean) -> Unit) {
        db_ref.child("tienda").child("usuarios").get()
            .addOnSuccessListener { snapshot ->
                var usuarioEncontrado = false
                for (usuarioSnapshot in snapshot.children) {
                    val usuario = usuarioSnapshot.getValue(Usuario::class.java)
                    if (usuario != null && usuario.nombre == nombre && usuario.contrasenia == contrasenia) {
                        usuarioEncontrado = true
                        break
                    }
                }

                if (usuarioEncontrado) {
                    callback(true) // Usuario y contraseña correctos
                } else {
                    callback(false) // Usuario no encontrado o contraseña incorrecta
                }
            }
            .addOnFailureListener {
                callback(false) // Error en la consulta
            }
    }

}