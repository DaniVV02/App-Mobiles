package com.example.partie1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class FragmentListePlannings : Fragment() {

    private lateinit var listView: ListView
    private lateinit var btnToutEffacer: Button
    private lateinit var btnRetour: Button
    private lateinit var viewModel: PlanningViewModel
    private lateinit var adapter: PlanningAdapter
    private lateinit var tvTitre: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vue = inflater.inflate(R.layout.liste_plannings, container, false)

        listView = vue.findViewById(R.id.listePlannings)
        btnToutEffacer = vue.findViewById(R.id.btnToutEffacer)
        btnRetour = vue.findViewById(R.id.btnRetour)
        tvTitre = vue.findViewById(R.id.tvTitrePlannings)

        viewModel = ViewModelProvider(requireActivity())[PlanningViewModel::class.java]

        val emailUser = arguments?.getString("emailUser") ?: ""
        val usernameUser = arguments?.getString("usernameUser") ?: ""

        val identifiant = if (emailUser.isNotEmpty()) emailUser else usernameUser

        viewModel.chargerPlannings(identifiant)

        // ⚠️ Assurez-vous d’avoir appelé setEmail("email_du_user") avant d’ouvrir ce fragment
        adapter = PlanningAdapter(requireContext(), emptyList(),
            onSupprimer = { planning -> viewModel.supprimer(planning) },
            onModifier = { planning -> afficherPopupModification(planning) }
        )

        //tvTitre.text = "Plannings de $usernameUser"

        tvTitre.text = when {
            emailUser.isNotEmpty() -> "📋 Plannings de $emailUser"
            usernameUser.isNotEmpty() -> "📋 Plannings de $usernameUser"
            else -> "📋 Plannings"
        }


        listView.adapter = adapter

        viewModel.planningsParEmail.observe(viewLifecycleOwner) { liste ->
            adapter.majListe(liste)
        }

        btnToutEffacer.setOnClickListener {
            viewModel.supprimerTous(emailUser)
        }

        btnRetour.setOnClickListener {
            requireActivity().finish()
        }

        return vue
    }

    private fun afficherPopupModification(planning: Planning) {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val champs = List(4) { EditText(requireContext()) }

        champs[0].setText(planning.creneau1)
        champs[1].setText(planning.creneau2)
        champs[2].setText(planning.creneau3)
        champs[3].setText(planning.creneau4)

        champs.forEach {
            layout.addView(it)
            it.hint = "Entrée"
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Modifier Planning")
            .setView(layout)
            .setPositiveButton("Valider") { _, _ ->
                val maj = planning.copy(
                    creneau1 = champs[0].text.toString(),
                    creneau2 = champs[1].text.toString(),
                    creneau3 = champs[2].text.toString(),
                    creneau4 = champs[3].text.toString()
                )
                viewModel.modifier(maj)
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
}