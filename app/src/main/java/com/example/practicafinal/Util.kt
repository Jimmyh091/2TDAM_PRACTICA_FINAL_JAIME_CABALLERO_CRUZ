package com.example.practicafinal

//import com.google.firebase.database.DatabaseReference
import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.eventos.Evento
import com.example.practicafinal.pedidos.Pedido
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class Util {
    companion object{

        // USUARIO //

        fun obtenerUsuario(db_ref : DatabaseReference, nombre : String, callback : (Usuario?) -> Unit) {
            db_ref.child("tienda").child("usuarios").child(nombre).get()
                .addOnSuccessListener { snapshot ->
                    val usuario = snapshot.getValue(Usuario::class.java)
                    callback(usuario)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }

        fun subirUsuario(db_ref: DatabaseReference, usuario: Usuario) {
            db_ref.child("tienda").child("usuarios").setValue(usuario)
        }

        fun borrarUsuario(db_ref: DatabaseReference, nombre: String) {
            db_ref.child("tienda").child("usuarios").child(nombre).removeValue()
        }

        fun modificarUsuario(db_ref: DatabaseReference, nombre: String, usuario: Usuario) {
            db_ref.child("tienda").child("usuarios").child(nombre).setValue(usuario)
        }

        fun obtenerUsuarios(db_ref: DatabaseReference): List<Usuario> {
            var usuarios = listOf<Usuario>()
            db_ref.child("tienda").child("usuarios").get().addOnSuccessListener {
                usuarios = it.children.map { it.getValue(Usuario::class.java)!! }
            }
            return usuarios
        }


        // CARTAS //

        fun obtenerCarta(db_ref: DatabaseReference, idCarta: String, callback: (Carta?) -> Unit) {
            db_ref.child("tienda").child("cartas").child(idCarta).get()
                .addOnSuccessListener { snapshot ->
                    val carta = snapshot.getValue(Carta::class.java)
                    callback(carta)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }

        fun subirCarta(db_ref: DatabaseReference, carta: Carta) {
            db_ref.child("tienda").child("cartas").child(carta.nombre).setValue(carta)
        }

        fun borrarCarta(db_ref: DatabaseReference, idCarta: String) {
            db_ref.child("tienda").child("cartas").child(idCarta).removeValue()
        }

        fun modificarCarta(db_ref: DatabaseReference, idCarta: String, carta: Carta) {
            db_ref.child("tienda").child("cartas").child(idCarta).setValue(carta)
        }


        // EVENTOS //

        fun obtenerEvento(db_ref: DatabaseReference, idEvento: String, callback: (Evento?) -> Unit) {
            db_ref.child("tienda").child("eventos").child(idEvento).get()
                .addOnSuccessListener { snapshot ->
                    val evento = snapshot.getValue(Evento::class.java)
                    callback(evento)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }

        fun subirEvento(db_ref: DatabaseReference, evento: Evento) {
            db_ref.child("tienda").child("eventos").child(evento.nombre).setValue(evento)
        }

        fun borrarEvento(db_ref: DatabaseReference, idEvento: String) {
            db_ref.child("tienda").child("eventos").child(idEvento).removeValue()
        }

        fun modificarEvento(db_ref: DatabaseReference, idEvento: String, evento: Evento) {
            db_ref.child("tienda").child("eventos").child(idEvento).setValue(evento)
        }


        // PEDIDOS //

        fun obtenerPedido(db_ref: DatabaseReference, idPedido: String, callback: (Pedido?) -> Unit) {
            db_ref.child("tienda").child("pedidos").child(idPedido).get()
                .addOnSuccessListener { snapshot ->
                    val pedido = snapshot.getValue(Pedido::class.java)
                    callback(pedido)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }
        fun subirPedido(db_ref: DatabaseReference, pedido: Pedido) {
            db_ref.child("tienda").child("pedidos").child(pedido.id).setValue(pedido)
        }
        fun borrarPedido(db_ref: DatabaseReference, idPedido: String) {
            db_ref.child("tienda").child("pedidos").child(idPedido).removeValue()
        }
        fun modificarPedido(db_ref: DatabaseReference, idPedido: String, pedido: Pedido) {
            db_ref.child("tienda").child("pedidos").child(idPedido).setValue(pedido)
        }


        /* ROBADO


        // mio pero mal
        fun obtenerUsaario(db_ref : DatabaseReference, nombre : String): Usuario? {
            var usuario: Usuario? = null
            db_ref.child("tienda").child("usuarios").child(nombre).get().addOnSuccessListener {
                usuario = it.getValue(Usuario::class.java)!!
            }
            return usuario
        }

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