package com.example.partie2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn8 = findViewById<Button>(R.id.btnExo8)
        val btn9 = findViewById<Button>(R.id.btnExo9)

        btn8.setOnClickListener {
            startActivity(Intent(this, Exo8Activity::class.java))
        }

        btn9.setOnClickListener {
            startActivity(Intent(this, Exo9Activity::class.java))
        }
    }
}
