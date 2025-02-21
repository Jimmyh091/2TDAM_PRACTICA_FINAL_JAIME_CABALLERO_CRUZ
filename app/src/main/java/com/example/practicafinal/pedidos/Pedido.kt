package com.example.practicafinal.pedidos

import java.time.LocalDateTime

data class Pedido (
    var id_firebase : String? = null,
    var usuario : String = "USER",
    var carta : String = "CARD",
    var fecha : String = LocalDateTime.now().toString(),
    var procesado : Boolean = false
)