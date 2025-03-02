package com.example.partie2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PaysAdapter(private val paysList: List<Pays>, private val onItemClick: (Pays) -> Unit) :
    RecyclerView.Adapter<PaysAdapter.PaysViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (paysList[position].nom) {
            "France" -> 1
            "Allemagne" -> 2
            "Espagne" -> 3
            "Perou" -> 4
            "Brazil" -> 5
            "Argentine" -> 6
            "Egypte" -> 7

            else -> 1 // par défaut
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaysViewHolder {
        val layoutResId = when (viewType) {
            1 -> R.layout.item_france  // Layout spécifique pour la France
            2 -> R.layout.item_allemagne //  pour l'Allemagne
            3 -> R.layout.item_espagne //  l'Espagne
            4 -> R.layout.item_perou //  pour le perou
            5 -> R.layout.item_brazil //  pour le brésil
            6 -> R.layout.item_arg //  pour l'argentine
            7 -> R.layout.item_egypt //  pour l'egypte


            else -> R.layout.item_pays  // par défaut
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return PaysViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaysViewHolder, position: Int) {
        val pays = paysList[position]
        holder.nomPays.text = pays.nom

        // gestion des images selon le pays
        when (pays.nom) {
            "France" -> holder.imagePays.setImageResource(R.drawable.france)
            "Allemagne" -> holder.imagePays.setImageResource(R.drawable.allemagne)
            "Espagne" -> holder.imagePays.setImageResource(R.drawable.espagne)
            "Perou" -> holder.imagePays.setImageResource(R.drawable.perou)
            "Brazil" -> holder.imagePays.setImageResource(R.drawable.brazil)
            "Argentine" -> holder.imagePays.setImageResource(R.drawable.drap_arg)
            "Egypte" -> holder.imagePays.setImageResource(R.drawable.drap_egypt)


            else -> holder.imagePays.setImageResource(R.drawable.france)
        }

        holder.itemView.setOnClickListener { onItemClick(pays) }
    }

    override fun getItemCount() = paysList.size

    class PaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomPays: TextView = itemView.findViewById(R.id.nomPays)
        val imagePays: ImageView = itemView.findViewById(R.id.imgDrapeau)
    }
}
