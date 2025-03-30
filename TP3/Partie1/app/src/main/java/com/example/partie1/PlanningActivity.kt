package com.example.partie1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanningActivity : AppCompatActivity() {

    private lateinit var etJour: EditText
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText
    private lateinit var et4: EditText
    private lateinit var database: AppDB
    private lateinit var btnVoirListe: Button
    private lateinit var btnDeconnexion: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning)

        etJour = findViewById(R.id.etJour)
        et1 = findViewById(R.id.etCreneau1)
        et2 = findViewById(R.id.etCreneau2)
        et3 = findViewById(R.id.etCreneau3)
        et4 = findViewById(R.id.etCreneau4)
        val btnValider = findViewById<Button>(R.id.btnValiderPlanning)
        val btnAfficherPlanning = findViewById<Button>(R.id.btnAfficherPlanning)
        btnVoirListe = findViewById(R.id.btnVoirListe)

        btnDeconnexion = findViewById(R.id.btnDeconnexion)

        btnDeconnexion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("afficherLogin", true)
            startActivity(intent)

            Toast.makeText(this, "Déconnexion réussie", Toast.LENGTH_SHORT).show()

            finish()
        }



        database = AppDB.getInstance(this)

        val email = intent.getStringExtra("email") ?: return
        val nomUtilisateur = intent.getStringExtra("nomUtilisateur") ?: return

        val identifiant = email.ifEmpty { nomUtilisateur }

        // on cache le bouton par défaut
        btnVoirListe.visibility = Button.GONE

        // si l'utilisateur est l'admin, on affiche le bouton
        if ((email == "admin@gmail.com" || nomUtilisateur == "admin")) {
            btnVoirListe.visibility = Button.VISIBLE
        }

        btnVoirListe.setOnClickListener {
            val intent = Intent(this, ListeUtilisateursActivity::class.java)
            startActivity(intent)
        }




        btnValider.setOnClickListener {
            val planning = Planning(
                userEmail = email,
                username = nomUtilisateur,
                jour = etJour.text.toString(),
                creneau1 = et1.text.toString(),
                creneau2 = et2.text.toString(),
                creneau3 = et3.text.toString(),
                creneau4 = et4.text.toString()
            )

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    database.planningDao().inserer(planning)
                }

                val intent = Intent(this@PlanningActivity, SynthesePlanningActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }

        btnAfficherPlanning.setOnClickListener {
            val intent = Intent(this@PlanningActivity, ListePlanningsActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("nomUtilisateur", nomUtilisateur)
            startActivity(intent)
        }
    }
}
