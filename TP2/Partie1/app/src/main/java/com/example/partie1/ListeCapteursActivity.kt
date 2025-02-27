package com.example.partie1

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.ComponentActivity.SENSOR_SERVICE

class ListeCapteursActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capteurs)

        // Récupérer le gestionnaire des capteurs
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensorList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Extraire les noms des capteurs
        val sensorNames = sensorList.map { it.name }

        // Trouver le ListView et y attacher un adaptateur
        val listView = findViewById<ListView>(R.id.listViewSensors)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorNames)
        listView.adapter = adapter

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