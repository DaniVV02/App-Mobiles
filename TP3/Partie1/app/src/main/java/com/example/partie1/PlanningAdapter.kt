package com.example.partie1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class PlanningAdapter (
    private val context: Context,
    private var plannings: List<Planning>,
    private val onSupprimer: (Planning) -> Unit,
    private val onModifier: (Planning) -> Unit
    ) : BaseAdapter() {

        fun majListe(nouvelleListe: List<Planning>) {
            plannings = nouvelleListe
            notifyDataSetChanged()
        }

        override fun getCount(): Int = plannings.size
        override fun getItem(position: Int): Any = plannings[position]
        override fun getItemId(position: Int): Long = plannings[position].id.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vue = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_planning, parent, false)

            val planning = plannings[position]

            val tvInfos = vue.findViewById<TextView>(R.id.tvInfos)
            val btnSupprimer = vue.findViewById<ImageButton>(R.id.btnSupprimer)
            val btnModifier = vue.findViewById<ImageButton>(R.id.btnModifier)

            tvInfos.text = "Jour : ${planning.jour}\n - 8h-10h : ${planning.creneau1}\n - 10h-12h : ${planning.creneau2}\n - 14h-16h : ${planning.creneau3}\n - 16h-18h : ${planning.creneau4}"

            btnSupprimer.setOnClickListener { onSupprimer(planning) }
            btnModifier.setOnClickListener { onModifier(planning) }

            return vue
        }
    }
