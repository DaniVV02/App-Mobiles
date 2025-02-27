package com.example.partie1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class ProximityActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private lateinit var imageViewProximity: ImageView
    private lateinit var textViewStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        imageViewProximity = findViewById(R.id.imageViewProximity)
        textViewStatus = findViewById(R.id.textViewStatus)

        // Initialisation du capteur de proximité
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (proximitySensor == null) {
            textViewStatus.text = "❌ Capteur de proximité non disponible"
        }
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            val distance = event.values[0]

            when {
                distance < 1 -> {  // Très proche
                    imageViewProximity.setImageResource(R.drawable.very_near)
                    textViewStatus.text = "Très proche 🔴"
                }
                distance in 1.0..3.0 -> {  // Proche
                    imageViewProximity.setImageResource(R.drawable.near)
                    textViewStatus.text = "Proche 🟠"
                }
                else -> {  // Pas proche
                    imageViewProximity.setImageResource(R.drawable.far)
                    textViewStatus.text = "Pas proche 🟢"
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Pas besoin d'implémenter cela
    }

}