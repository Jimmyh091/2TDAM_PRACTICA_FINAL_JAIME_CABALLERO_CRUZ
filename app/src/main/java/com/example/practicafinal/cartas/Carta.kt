package com.example.practicafinal.cartas

data class Carta (
    var nombre: String = "Card",
    var descripcion: String = "Description",
    var precio : Float = -1f,
    var imagen : String? = null,
    var stock : Int = -1
)