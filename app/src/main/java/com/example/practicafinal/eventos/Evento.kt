package com.example.practicafinal.eventos

import com.example.practicafinal.Usuario

class Evento (
    var nombre: String,
    var descripcion: String,
    var fecha: String,
    var precio: Float,
    var aforo_max: Int,
    var imagen : String?,
    var usuarios_apuntados : MutableList<Usuario> /*o de nombres de usuarios*/) {
}