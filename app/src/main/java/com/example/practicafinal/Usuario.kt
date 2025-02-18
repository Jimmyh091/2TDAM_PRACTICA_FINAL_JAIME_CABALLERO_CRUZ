package com.example.practicafinal

import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.eventos.Evento

data class Usuario (
    var admin : Boolean = false,
    var nombre : String = "User",
    var correo : String = "error@gmail.com",
    var contrasenia : String = "",
    var imagen : String? = null,
    var dinero : Float = 0f,
    var cartas_compradas : MutableList<Carta> = mutableListOf(),
    var eventos_apuntados : MutableList<Evento> = mutableListOf()
)