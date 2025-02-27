package com.example.partie1

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity


class SensorCheckActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        /*
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
*/

        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        // Liste des capteurs à vérifier
        val sensorsToCheck = mapOf(
            "Accéléromètre" to Sensor.TYPE_ACCELEROMETER,
            "Gyroscope" to Sensor.TYPE_GYROSCOPE,
            "Gravité" to Sensor.TYPE_GRAVITY,
            "Accélération linéaire" to Sensor.TYPE_LINEAR_ACCELERATION,
            "Vecteur de rotation" to Sensor.TYPE_ROTATION_VECTOR,
            "Vecteur de rotation (jeu)" to Sensor.TYPE_GAME_ROTATION_VECTOR,
            "Vecteur de rotation (géomagnétique)" to Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
            "Capteur de lumière" to Sensor.TYPE_LIGHT,
            "Pression (baromètre)" to Sensor.TYPE_PRESSURE,
            "Température ambiante" to Sensor.TYPE_AMBIENT_TEMPERATURE,
            "Humidité relative" to Sensor.TYPE_RELATIVE_HUMIDITY,
            "Champ magnétique" to Sensor.TYPE_MAGNETIC_FIELD,
            "Capteur de proximité" to Sensor.TYPE_PROXIMITY,
            "Fréquence cardiaque" to Sensor.TYPE_HEART_RATE,
            "Podomètre (compteur de pas)" to Sensor.TYPE_STEP_COUNTER,
            "Détection de pas" to Sensor.TYPE_STEP_DETECTOR,
            "Mouvement significatif" to Sensor.TYPE_SIGNIFICANT_MOTION
        )

        val availableSensors = mutableListOf<String>()
        val missingSensors = mutableListOf<String>()

        for ((sensorName, sensorType) in sensorsToCheck) {
            if (sensorManager.getDefaultSensor(sensorType) != null) {
                availableSensors.add("✅ $sensorName : Disponible")
            } else {
                missingSensors.add("❌ $sensorName : Indisponible")
            }
        }


        //val message = StringBuilder("Disponibilité des capteurs :\n")
/*
        if (accelerometer != null) {
            message.append("✅ Accéléromètre : Disponible\n")
        } else {
            message.append("❌ Accéléromètre : Indisponible\n")
        }

        if (gyroscope != null) {
            message.append("✅ Gyroscope : Disponible\n")
        } else {
            message.append("❌ Gyroscope : Indisponible\n")
        }

 */

        // Affichage des résultats
        val message = StringBuilder("🔎 Vérification des capteurs :\n\n")
        message.append(availableSensors.joinToString("\n") + "\n\n")
        message.append(missingSensors.joinToString("\n"))

        textViewResult.text = message.toString()

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
