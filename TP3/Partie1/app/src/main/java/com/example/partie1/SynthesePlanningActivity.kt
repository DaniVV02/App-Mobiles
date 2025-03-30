package com.example.partie1

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SynthesePlanningActivity : AppCompatActivity() {

    private lateinit var tvSynthese: TextView
    private lateinit var database: AppDB
    private lateinit var btnRetour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synthese_planning)

        tvSynthese = findViewById(R.id.tvSynthese)
        database = AppDB.getInstance(this)
        btnRetour = findViewById(R.id.btnRetour)

        val email = intent.getStringExtra("email") ?: return

        lifecycleScope.launch {
            val planning = withContext(Dispatchers.IO) {
                database.planningDao().getDernierPlanning(email)
            }

            tvSynthese.text = """
                📅 Planning du jour : ${planning?.jour} :
                08h-10h : ${planning?.creneau1}
                10h-12h : ${planning?.creneau2}
                14h-16h : ${planning?.creneau3}
                16h-18h : ${planning?.creneau4}
            """.trimIndent()
        }

        btnRetour.setOnClickListener {
            finish()
        }


    }
}
