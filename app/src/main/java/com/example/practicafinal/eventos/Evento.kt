package com.example.practicafinal.eventos

import com.example.practicafinal.Usuario

data class Evento (
    var nombre: String = "Event",
    var descripcion: String = "Description",
    var fecha: String = ""/*?*/,
    var precio: Float = -1f,
    var aforo_max: Int = -1,
    var imagen : String? = null,
    var usuarios_apuntados : MutableList<Usuario> = mutableListOf()/*o de nombres de usuarios*/
)