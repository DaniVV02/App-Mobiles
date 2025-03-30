package com.example.partie1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentInscription())
                .commit()
        }

        val afficherLogin = intent.getBooleanExtra("afficherLogin", false)

        if (afficherLogin) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentLogin())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentInscription())
                .commit()
        }
        /*
                val btnVoirListe = findViewById<Button>(R.id.btnVoirListe)
                btnVoirListe.setOnClickListener {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, FragmentListeUtilisateurs())
                        .addToBackStack(null)
                        .commit()
                }

         */
    }
}

