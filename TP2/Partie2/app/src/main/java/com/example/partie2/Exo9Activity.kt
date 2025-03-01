package com.example.partie2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Exo9Activity : AppCompatActivity(), ListePaysFragment.OnPaysSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_fragment)

        // fragment de la liste au démarrage
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag1, ListePaysFragment())
                .commit()
        }

        val btnRetourAccueil = findViewById<Button>(R.id.btnRetourAccueil)
        // Bouton retour vers l'accueil
        btnRetourAccueil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    // fragment detail + image
    override fun onPaysSelected(pays: Pays) {
        val detailFragment = DetailsPaysFragment.newInstance(pays)
        val imgFragment = ImgPaysFragment.newInstance(pays)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frag2, detailFragment)
            .replace(R.id.frag3, imgFragment)

            .addToBackStack(null)
            .commit()
    }

}
