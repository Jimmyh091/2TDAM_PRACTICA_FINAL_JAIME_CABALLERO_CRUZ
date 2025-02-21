package com.example.practicafinal.pedidos

import java.time.LocalDateTime

data class Pedido (
    var usuario : String,
    var carta : String,
    var fecha : String = LocalDateTime.now().toString(),
    var procesado : Boolean = false
)