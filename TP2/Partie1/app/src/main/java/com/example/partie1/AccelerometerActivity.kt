package com.example.partie1

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class AccelerometerActivity : ComponentActivity() , SensorEventListener{
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var layout: LinearLayout
    private lateinit var textViewValues: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer)

        layout = findViewById(R.id.accelerometerLayout)
        textViewValues = findViewById(R.id.textViewValues)

        // Initialisation du capteur d'accélération
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) {
            textViewValues.text = "❌ Accéléromètre non disponible sur cet appareil"
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

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val gravity = 9.81f // Gravité terrestre
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Calculer l'intensité de l'accélération
            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat() - gravity

            // Définir la couleur en fonction des valeurs
            when {
                acceleration < 2 -> layout.setBackgroundColor(getColor(R.color.green)) // Valeurs basses
                acceleration in 2.0..5.0 -> layout.setBackgroundColor(getColor(R.color.black))  // Valeurs moyennes
                acceleration > 8 -> layout.setBackgroundColor(getColor(R.color.red))  // Valeurs hautes
            }

            // Affichage des valeurs dans le TextView
            textViewValues.text = "Accélération : ${"%.2f".format(acceleration)} m/s²"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ne rien faire pour cet exercice
    }

}