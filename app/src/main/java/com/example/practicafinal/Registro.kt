package com.example.practicafinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicafinal.databinding.ActivityRegistroBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var db_ref: DatabaseReference

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

            Log.v("yo", "registro")

            val usuario = binding.registroTietUsuario.text.toString()
            val correo = binding.registroTietCorreo.text.toString()
            val contrasenia = binding.registroTietContrasenia.text.toString()
            val contrasenia2 = binding.registroTietRepetirContrasenia.text.toString()

            db_ref = FirebaseDatabase.getInstance().getReference()
            var listaUser = Util.obtenerUsuarios(db_ref)

            Log.v("yo", listaUser.toString())

            Log.v("yo", usuario)
            Log.v("yo", correo)
            Log.v("yo", contrasenia)
            Log.v("yo", contrasenia2)

            if (
                usuario.isEmpty() ||
                correo.isEmpty() ||
                contrasenia.isEmpty() ||
                contrasenia2.isEmpty()
            ){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }else if (!validaContrasenia(binding.registroTietContrasenia.text.toString(), binding.registroTietRepetirContrasenia.text.toString())){

            }else if (!validaCorreo(binding.registroTietCorreo.text.toString())){

            }else if (!validaUsuario(binding.registroTietUsuario.text.toString())){

            }else{

                var valido = true

                for (user in listaUser){
                    if (usuario == user.nombre) {

                        valido = false
                        break
                    }
                }

                if (valido){

                    Log.v("yo", "valido")

                    var usuario = Usuario(
                        admin = false,
                        nombre = usuario,
                        correo = correo,
                        contrasenia = contrasenia,
                        dinero = 0f
                    )

                    Util.subirUsuario(db_ref, usuario)

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    actualizarShared(usuario)

                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    fun validaContrasenia(contrasenia: String, contrasenia2: String) : Boolean{

        if (contrasenia != contrasenia2){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }else if (contrasenia.length < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }

    }

    fun validaCorreo(correo: String) : Boolean{

        if (!correo.contains("@")){
            Toast.makeText(this, "El correo no es válido", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }

    }

    fun validaUsuario(usuario: String) : Boolean{

        val listaUser = Util.obtenerUsuarios(db_ref)

        for (user in listaUser) {
            if (usuario == user.nombre) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
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