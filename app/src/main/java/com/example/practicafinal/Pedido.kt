package com.example.practicafinal

import com.example.practicafinal.cartas.Carta

data class Pedido (
    var usuario : String,
    var carta : String,
    var fecha : String,
    var procesado : Boolean = false
)