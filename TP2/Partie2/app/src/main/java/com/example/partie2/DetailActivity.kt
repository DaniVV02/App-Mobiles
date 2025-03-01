package com.example.partie2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nom = intent.getStringExtra("nom")
        val capitale = intent.getStringExtra("capitale")
        val population = intent.getIntExtra("population", 0)


        // pour séledctionner le bon layout en fonction du pays
        val layoutResId = when (nom) {
            "France" -> R.layout.detail_france
            "Allemagne" -> R.layout.detail_allemagne
            "Perou" -> R.layout.detail_perou
            "Espagne" -> R.layout.detail_espagne
            "Brazil" -> R.layout.detail_brazil

            else -> R.layout.activity_detail
        }
        setContentView(layoutResId)

        findViewById<TextView>(R.id.txtNom).text = "Pays : $nom"
        findViewById<TextView>(R.id.txtCapitale).text = "Capitale : $capitale"
        findViewById<TextView>(R.id.txtPopulation).text = "Population : $population"

        val btnRetourAccueil = findViewById<Button>(R.id.btnRetourAccueil)
        // Bouton retour vers l'accueil
        btnRetourAccueil.setOnClickListener {
            val intent = Intent(this, Exo8Activity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
