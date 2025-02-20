package com.example.practicafinal.menu

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R
import com.example.practicafinal.eventos.Evento

class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvNombre: TextView = itemView.findViewById(R.id.itemEvento_nombre)
    private val tvImage: ImageView = itemView.findViewById(R.id.itemEvento_foto)

    // Cambiar el parámetro a 'Evento'
    fun bind(evento: Evento) {
        tvNombre.text = evento.nombre
        // Si tienes una imagen en tu clase Evento, puedes cargarla aquí
        // Por ejemplo, si tienes una URL o un recurso, reemplaza esto:
        tvImage.setImageResource(R.drawable.archivovacio) // Esto es solo un ejemplo
    }
}
