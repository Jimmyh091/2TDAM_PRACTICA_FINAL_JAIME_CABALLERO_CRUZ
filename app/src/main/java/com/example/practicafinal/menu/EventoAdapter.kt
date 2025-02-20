package com.example.practicafinal.menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal.R
import com.example.practicafinal.eventos.Evento
import com.example.practicafinal.eventos.VerEvento

class EventoAdapter(private val eventos: List<Evento>, private val listener: onEventoClickedListener) : RecyclerView.Adapter<EventoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)

        // Evento al hacer clic en un evento
        holder.itemView.setOnClickListener {
            // Crear un intent para abrir la vista del evento
            val intent = Intent(holder.itemView.context, VerEvento::class.java)
            // Asegúrate de pasar el dato necesario, en este caso, el nombre del evento
            intent.putExtra("evento_nombre", evento.nombre)
            holder.itemView.context.startActivity(intent)

            // Notificar al listener que se ha hecho clic en el evento
            listener.onEventoClicked(evento)
        }
    }
}