package com.example.practicafinal

import com.example.practicafinal.cartas.Carta

data class Pedido (
    var usuario : Usuario,
    var carta : Carta,
    var procesado : Boolean = false
)