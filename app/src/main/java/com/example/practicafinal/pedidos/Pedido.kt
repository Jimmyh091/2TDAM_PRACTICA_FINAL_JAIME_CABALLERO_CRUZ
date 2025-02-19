package com.example.practicafinal.pedidos

data class Pedido (
    var usuario : String,
    var carta : String,
    var fecha : String,
    var procesado : Boolean = false
)