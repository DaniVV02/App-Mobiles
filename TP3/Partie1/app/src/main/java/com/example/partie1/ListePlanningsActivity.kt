package com.example.partie1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ListePlanningsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_liste_plannings)

        val email = intent.getStringExtra("email") ?: ""
        val username = intent.getStringExtra("nomUtilisateur") ?: ""

        val fragment = FragmentListePlannings().apply {
            arguments = Bundle().apply {
                putString("emailUser", email)
                putString("usernameUser", username)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerPlanning, fragment)
            .commit()
    }
}
