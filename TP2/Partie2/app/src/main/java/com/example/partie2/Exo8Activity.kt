package com.example.partie2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Exo8Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo8)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val paysList = listOf(
            Pays("France", "Paris", 67000000),
            Pays("Allemagne", "Berlin", 83000000),
            Pays("Espagne", "Madrid", 49000000),
            Pays("Perou", "Lima", 31914989),
            Pays("Brazil", "Brasilia", 212583000)

        )

        val adapter = PaysAdapter(paysList) { pays ->

            val layoutResId = when (pays.nom) {
                "France" -> R.layout.detail_france
                "Allemagne" -> R.layout.detail_allemagne
                "Perou" -> R.layout.detail_perou
                "Espagne" -> R.layout.detail_espagne
                "Brazil" -> R.layout.detail_brazil

                else -> R.layout.activity_detail
            }
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("layoutResId", layoutResId)
                putExtra("nom", pays.nom)
                putExtra("capitale", pays.capitale)
                putExtra("population", pays.population)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter


        val btnRetourAccueil = findViewById<Button>(R.id.btnRetourAccueil)
        // Bouton retour vers l'accueil
        btnRetourAccueil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
