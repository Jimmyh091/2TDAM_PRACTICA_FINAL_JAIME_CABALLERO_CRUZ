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

        fun subirUsuario(db_ref: DatabaseReference, usuario: Usuario) {
            db_ref.child("tienda").child("usuarios").setValue(usuario)
        }

        /* ROBADO

        fun anadir_usuario(db_ref: DatabaseReference, id: String, usuario: Usuario) {
            db_ref.child("usuarios").child(id).setValue(usuario)
        }

        fun actualizar_carta(db_ref: DatabaseReference, id: String, carta: Carta){
            db_ref.child("cartas").child(id).setValue(carta)
        }

        //actualizar usuario
        fun actualizar_usuario(db_ref: DatabaseReference, id: String, usuario: Usuario){
            db_ref.child("usuarios").child(id).setValue(usuario)
        }

        //conseguir objeto usuario desde firebase pasandole el id
        fun obtener_usuario(db_ref: DatabaseReference, id: String): Usuario? {
            var usuario: Usuario? = null
            db_ref.child("usuarios").child(id).get().addOnSuccessListener {
                usuario = it.getValue(Usuario::class.java)
            }
            return usuario
        }

        fun anadir_carta(db_ref: DatabaseReference, id: String, carta: Carta){
            db_ref.child("cartas").child(id).setValue(carta)
        }

        fun anadir_evento(db_ref: DatabaseReference, id: String, evento: Evento){
            db_ref.child("eventos").child(id).setValue(evento)
        }

        fun existe_evento(lista_eventos: List<Evento>, nombre: String): Boolean{
            return lista_eventos.any{ it.nombre!!.lowercase() == nombre.lowercase()}
        }

        fun existeUsuario(usuarios: List<Usuario>, nombre: String): Boolean {
            return usuarios.any { it.nombre!!.lowercase() == nombre.lowercase() }
        }

        fun toastCorrutina(activity: AppCompatActivity, contexto: Context, texto: String) {
            activity.runOnUiThread {
                Toast.makeText(contexto, texto, Toast.LENGTH_SHORT).show()
            }
        }

        fun opcionesGlide(context: Context): RequestOptions {
            val options = RequestOptions()
                .placeholder(animacion_carga(context))
                .error(R.drawable.magic_tras)
            return options
        }

        val transicion = DrawableTransitionOptions.withCrossFade(500)

        fun animacion_carga(contexto: Context): CircularProgressDrawable {
            val animacion = CircularProgressDrawable(contexto)
            animacion.strokeWidth = 5f
            animacion.centerRadius = 30f
            animacion.start()

            return animacion
        }

        */

    }
}