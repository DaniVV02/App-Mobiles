package com.example.partie1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class UtilisateurAdapter(
    private val context: Context,
    private var utilisateurs: List<Utilisateur>,
    private val onSupprimer: (Utilisateur) -> Unit,
    private val onModifier: (Utilisateur) -> Unit
) : BaseAdapter() {

    fun majListe(nouvelleListe: List<Utilisateur>) {
        utilisateurs = nouvelleListe
        notifyDataSetChanged()
    }

    override fun getCount(): Int = utilisateurs.size
    override fun getItem(position: Int): Any = utilisateurs[position]
    override fun getItemId(position: Int): Long = utilisateurs[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vue = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_utilisateur, parent, false)

        val utilisateur = utilisateurs[position]

        val tvInfos = vue.findViewById<TextView>(R.id.tvInfos)
        val btnSupprimer = vue.findViewById<ImageButton>(R.id.btnSupprimer)
        val btnModifier = vue.findViewById<ImageButton>(R.id.btnModifier)

        tvInfos.text = "${utilisateur.nom} - ${utilisateur.nomUtilisateur} - ${utilisateur.email}"

        btnSupprimer.setOnClickListener { onSupprimer(utilisateur) }
        btnModifier.setOnClickListener { onModifier(utilisateur) }

        return vue
    }
}
