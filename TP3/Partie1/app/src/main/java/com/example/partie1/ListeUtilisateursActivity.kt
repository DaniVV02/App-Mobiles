package com.example.partie1

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ListeUtilisateursActivity : AppCompatActivity() {

    private lateinit var viewModel: UtilisateurViewModel
    private lateinit var adapter: UtilisateurAdapter
    private lateinit var listView: ListView
    private lateinit var btnToutEffacer: Button
    private lateinit var btnRetour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_liste_utilisateurs)

        listView = findViewById(R.id.listeUtilisateurs)
        btnToutEffacer = findViewById(R.id.btnToutEffacer)
        btnRetour = findViewById(R.id.btnRetour)

        viewModel = ViewModelProvider(this)[UtilisateurViewModel::class.java]

        adapter = UtilisateurAdapter(this, emptyList(),
            onSupprimer = { utilisateur -> viewModel.supprimer(utilisateur) },
            onModifier = { utilisateur -> afficherPopupModification(utilisateur) }
        )

        listView.adapter = adapter

        viewModel.tousLesUtilisateurs.observe(this) {
            adapter.majListe(it)
        }

        btnToutEffacer.setOnClickListener {
            viewModel.supprimerTous()
        }

        btnRetour.setOnClickListener {
            finish()
        }
    }

    private fun afficherPopupModification(utilisateur: Utilisateur) {
        val editText = EditText(this)
        editText.setText(utilisateur.nom)

        AlertDialog.Builder(this)
            .setTitle("Modifier le nom")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                val nouveauNom = editText.text.toString()
                val utilisateurModifie = utilisateur.copy(nom = nouveauNom)
                viewModel.modifier(utilisateurModifie)
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
}
