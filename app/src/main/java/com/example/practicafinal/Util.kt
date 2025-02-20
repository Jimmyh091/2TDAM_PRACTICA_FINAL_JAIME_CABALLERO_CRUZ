package com.example.practicafinal

//import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class Util {
    companion object{

        fun obtenerUsuarios(db_ref : DatabaseReference): List<Usuario> {
            var usuarios = listOf<Usuario>()
            db_ref.child("tienda").child("usuarios").get().addOnSuccessListener {
                usuarios = it.children.map { it.getValue(Usuario::class.java)!! }
            }
            return usuarios
        }

        fun obtenerUsuario(db_ref : DatabaseReference, nombre : String): Usuario? {
            var usuario: Usuario? = null
            db_ref.child("tienda").child("usuarios").child(nombre).get().addOnSuccessListener {
                usuario = it.getValue(Usuario::class.java)!!
            }
            return usuario
        }

    }
}