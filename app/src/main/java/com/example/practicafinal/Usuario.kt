package com.example.practicafinal

import com.example.practicafinal.cartas.Carta
import com.example.practicafinal.eventos.Evento

class Usuario (
    var admin : Boolean,
    var nombre : String,
    var correo : String,
    var contrasenia : String,
    var imagen : String?,
    var cartas_compradas : MutableList<Carta> /*o de nombres de cartas?*/,
    var eventos_apuntados : MutableList<Evento> /*o de nombres de eventos?*/
)