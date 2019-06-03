package com.example.basketapp.matchAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basketapp.R
import com.example.basketapp.roomDatabase.entity.MatchEntity
import kotlinx.android.synthetic.main.info_match.view.*
import kotlinx.android.synthetic.main.main_content_recycler.view.*
import kotlinx.android.synthetic.main.match_cardview.view.*

class MatchAdapter (var items : List<MatchEntity>, val clickListener : (MatchEntity) -> Unit) : RecyclerView.Adapter<MatchAdapter.viewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_cardview,parent,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(items[position],clickListener)
    }

    fun dataChange(listaMatches : List<MatchEntity>){
        items = listaMatches
        notifyDataSetChanged()
    }

    class viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : MatchEntity, clickListener: (MatchEntity) -> Unit) = with(itemView){
            nombre_de_equipos_en_lista.text = item.name
            if(item.terminado == 1) {
                terminado_en_lista.text = "Terminado"
            } else{
                terminado_en_lista.text = "En progreso"
            }
            fecha_partido_en_lista.text = item.fecha
            hora_partido_en_lista.text = item.hora

            var scoreUnido : String = item.score1.toString() + " - " + item.score2.toString()
            score_en_lista.text = scoreUnido
            this.setOnClickListener{clickListener(item)}

        }

    }

}