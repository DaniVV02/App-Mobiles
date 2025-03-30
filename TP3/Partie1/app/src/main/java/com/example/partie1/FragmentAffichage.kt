package com.example.partie1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAffichage : Fragment() {

    private lateinit var tvInfos: TextView
    private lateinit var btnRetour: Button
    private lateinit var database: AppDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_affichage, container, false)

        tvInfos = view.findViewById(R.id.tvInfos)
        btnRetour = view.findViewById(R.id.btnRetour)

        database = AppDB.getInstance(requireContext())

        afficherInfosRoom()

        btnRetour.setOnClickListener {
            val previousFragment = parentFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (previousFragment is FragmentInscription) {
                previousFragment.viderChamps()
            }
            requireActivity().supportFragmentManager.popBackStack()

        }

        return view
    }

    private fun afficherInfosRoom() {
        lifecycleScope.launch {
            val utilisateur = withContext(Dispatchers.IO) {
                database.utilisateurDao().getPremierUtilisateur()
            }

            val infos = """
                👤 Nom et Prénom: ${utilisateur.nom} 
                👤 Nom d'utilisateur : ${utilisateur.nomUtilisateur}
                🎂 Date de naissance : ${utilisateur.dateNaissance}
                📞 Téléphone : ${utilisateur.telephone}
                📧 Email : ${utilisateur.email}
                🔒 Mot de passe : ${utilisateur.motDePasse}
                ⭐ Centres d’intérêt : ${utilisateur.centresInteret}
            """.trimIndent()

            tvInfos.text = infos
        }

    }
}